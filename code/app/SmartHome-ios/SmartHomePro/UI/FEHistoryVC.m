//
//  FEHistoryVC.m
//  SmartHome
//
//  Created by Seven on 15-5-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEHistoryVC.h"
#import "FEWebServiceManager.h"
#import "FECheckManageHistoryRequest.h"
#import "FECheckHistoryResponse.h"
#import "FEMemoryCache.h"
#import "FECompany.h"
#import "FEFireAlarm.h"
#import "NSDate+Formatter.h"
#import "FEFireAlarmHistoryRequest.h"
#import "FEFireAlarmHistoryResponse.h"
#import "FEDeviceDetailVC.h"
#import <ShareSDK/ShareSDK.h>
#import "Define.h"
#import "FEPopView.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface FEHistoryVC ()<UITableViewDataSource,UITableViewDelegate>
@property (nonatomic, strong) NSMutableArray *dataSource;
@end

@implementation FEHistoryVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"历史记录";
    self.dataSource = [NSMutableArray new];
    [self requsetHistory];
}

-(void)requsetHistory{
    __weak typeof(self) weakself = self;
    if (self.type == MANAGE) {
        FECheckManageHistoryRequest *rdata = [[FECheckManageHistoryRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID company:self.company.companyID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filer:nil];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECheckHistoryResponse class] response:^(NSError *error, id response) {
            FECheckHistoryResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                [weakself.dataSource addObjectsFromArray:rsp.checkLogList];
                [weakself.tableView reloadData];
            }
        }];
    }else if (self.type == FIRE_ALARM){
        FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"alarmKind" value:@"2"];
        FEFireAlarmHistoryRequest *rdata = [[FEFireAlarmHistoryRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID company:self.company.companyID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filer:@[attr]];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEFireAlarmHistoryResponse class] response:^(NSError *error, id response) {
            FEFireAlarmHistoryResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                [weakself.dataSource addObjectsFromArray:rsp.fireAlarmList];
                [weakself.tableView reloadData];
            }
        }];
    }else if (self.type == DEVICE_STATUS){
        FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"alarmKind" value:@"1"];
        FEFireAlarmHistoryRequest *rdata = [[FEFireAlarmHistoryRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID company:self.company.companyID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filer:@[attr]];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEFireAlarmHistoryResponse class] response:^(NSError *error, id response) {
            FEFireAlarmHistoryResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                [weakself.dataSource addObjectsFromArray:rsp.fireAlarmList];
                [weakself.tableView reloadData];
            }
        }];
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell;
    switch (self.type) {
        case FIRE_ALARM:{
            FEFireAlarm *alarm = self.dataSource[indexPath.row];
            cell = [tableView dequeueReusableCellWithIdentifier:@"alarmItemCell" forIndexPath:indexPath];
            cell.detailTextLabel.text = alarm.locationDesp;
            cell.textLabel.text = [[alarm.sensorTypeName stringByAppendingString:@"发生"] stringByAppendingString:alarm.alarmTypeName];
        }
        break;
        
        case DEVICE_STATUS:{
            FEFireAlarm *fireAlarm = _dataSource[indexPath.row];
            cell = [tableView dequeueReusableCellWithIdentifier:@"deviceStatusCell" forIndexPath:indexPath];
            if ([fireAlarm.alarmTypeName isEqualToString:@"集中器离线"]) {
                cell.textLabel.text = [NSString stringWithFormat:@"发生%@",fireAlarm.alarmTypeName];
                cell.detailTextLabel.text = [[NSDate dateWithTimeIntervalSince1970:fireAlarm.alarmTime.doubleValue / 1000] defaultFormat];
            }else{
                cell.textLabel.text = [NSString stringWithFormat:@"%@发生%@",fireAlarm.sensorTypeName,fireAlarm.alarmTypeName];
                cell.detailTextLabel.text = fireAlarm.locationDesp;
            }
        }
        
        break;
        case MANAGE:{
            FECheckLog *log = self.dataSource[indexPath.row];
            cell = [tableView dequeueReusableCellWithIdentifier:@"manageCell" forIndexPath:indexPath];
            UILabel *title = (UILabel *)[cell viewWithTag:1];
            UILabel *detail = (UILabel *)[cell viewWithTag:2];
            UIImageView *imageView = (UIImageView *)[cell viewWithTag:3];
            UILabel *status = (UILabel *)[cell viewWithTag:4];
            if (log.abnormalPic) {
                imageView.hidden = NO;
            }else{
                imageView.hidden = YES;
            }
            title.text = log.checkItem;
            detail.text = log.checkSys;
            NSString *result = @"";
            if (log.checkResult.integerValue == 0) {
                result = @"未设置";
            }else if (log.checkResult.integerValue == 1){
                result = @"正常";
            }else{
                result = @"异常";
            }
            status.text = result;
        }
        break;
        default:
        break;
    }
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.dataSource.count;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 55;
}

#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (self.type == MANAGE) {
        __weak typeof(self) weakself = self;
        FECheckLog *log = self.dataSource[indexPath.row];
        FEPopView *pview = [[FEPopView alloc] initFromView:self.view action:^{
            [weakself share:log];
        }];
        pview.contentLabel.text = log.abnormalDesp;
        [pview.imageView sd_setImageWithURL:[NSURL URLWithString:kImageURL(log.abnormalPic)]];
        pview.tlabel.text = log.checkItem;
        pview.personLabel.text = [NSString stringWithFormat:@"巡检员:%@",log.checker];
        
        NSDate *date = [NSDate dateWithTimeIntervalSince1970:log.checkTime.doubleValue / 1000];
        pview.timeLabel.text = [NSString stringWithFormat:@"巡检时间:%@",[date defaultFormat]];
        [pview show];

//        [self share:log];
    }else if (self.type == FIRE_ALARM){
        FEFireAlarm *alarm = self.dataSource[indexPath.row];
        [self performSegueWithIdentifier:@"toDeviceDetailSegue" sender:alarm];

    }else if (self.type == DEVICE_STATUS){
        
        FEFireAlarm *alarm = self.dataSource[indexPath.row];
        if (![alarm.alarmTypeName isEqualToString:@"集中器离线"]) {
            [self performSegueWithIdentifier:@"toDeviceDetailSegue" sender:alarm];
        }
    }
}

-(void)share:(FECheckLog *)log{
    //构造分享内容
    id<ISSContent> publishContent = [ShareSDK content:log.abnormalDesp
                                       defaultContent:@""
                                                image:[ShareSDK imageWithUrl:kImageURL(log.abnormalPic)]
                                                title:@"智慧消防"
                                                  url:kImageURL(log.abnormalPic)
                                          description:nil
                                            mediaType:SSPublishContentMediaTypeImage];
    
    
    [ShareSDK clientShareContent:publishContent //内容对象
                            type:ShareTypeWeixiSession //平台类型
                   statusBarTips:YES
                          result:^(ShareType type, SSResponseState state, id<ISSPlatformShareInfo> statusInfo, id<ICMErrorInfo> error, BOOL end) {//返回事件
                              
                              if (state == SSPublishContentStateSuccess)
                              {
                                  NSLog(NSLocalizedString(@"TEXT_SHARE_SUC", @"分享成功!"));
                              }
                              else if (state == SSPublishContentStateFail)
                              {
                                  UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示" message:@"分享失败" delegate:nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
                                  [alert show];
                                  NSLog(NSLocalizedString(@"TEXT_SHARE_FAI", @"分享失败!"), [error errorCode], [error errorDescription]);
                              }
                          }];
    
}

#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
    if ([segue.identifier isEqualToString:@"toDeviceDetailSegue"]) {
        FEDeviceDetailVC *vc = segue.destinationViewController;
        vc.company = self.company;
        vc.device = sender;
    }
    
}


@end

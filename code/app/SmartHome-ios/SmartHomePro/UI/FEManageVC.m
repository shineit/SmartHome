//
//  FEManageVC.m
//  SmartHome
//
//  Created by Seven on 15-4-5.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEManageVC.h"
#import "FECheckLogByIdRequest.h"
#import "FECheckLogByIdResponse.h"
#import "FEWebServiceManager.h"
#import "FEMemoryCache.h"
#import "FECompany.h"
#import <ShareSDK/ShareSDK.h>
#import "Define.h"
#import "FEPopView.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "NSDate+Formatter.h"
#import "FECommonDefine.h"
#import "FEHistoryVC.h"


@interface FEManageVC (){
    NSMutableArray *_checkLog;
}

@end

@implementation FEManageVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"智慧管理";
    _checkLog = [NSMutableArray new];
    [self requestLog];
}

-(void)requestLog{
    FECheckLogByIdRequest *rdata = [[FECheckLogByIdRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID comanyId:self.company.companyID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filterList:nil];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECheckLogByIdResponse class] response:^(NSError *error, id response) {
        FECheckLogByIdResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            [_checkLog addObjectsFromArray:rsp.checkLogList];
            [weakself.tableView reloadData];
        }
    }];
    
}


#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    FECheckLog *log = _checkLog[indexPath.row];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"manageCell" forIndexPath:indexPath];
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
    
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _checkLog.count;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    FECheckLog *log = _checkLog[indexPath.row];
    __weak typeof(self) weakself = self;
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
    
    
//    [ShareSDK oneKeyShareContent:publishContent//内容对象
//                       shareList:shareList//平台类型列表
//                     authOptions:nil//授权选项
//                   statusBarTips:YES
//                          result:^(ShareType type, SSResponseState state, id<ISSPlatformShareInfo> statusInfo, id<ICMErrorInfo> error, BOOL end) {//返回事件
//                              
//                              if (state == SSPublishContentStateSuccess)
//                              {
//                                  NSLog(NSLocalizedString(@"TEXT_SHARE_SUC", @"分享成功"));
//                              }
//                              else if (state == SSPublishContentStateFail)
//                              {
//                                  NSLog(NSLocalizedString(@"TEXT_SHARE_FAI", @"分享失败,错误码:%d,错误描述:%@"), [error errorCode], [error errorDescription]);
//                              }
//                          }];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
    if ([segue.identifier isEqualToString:@"historySegue"]) {
        FEHistoryVC *vc = segue.destinationViewController;
        vc.type = MANAGE;
        vc.company = self.company;
    }
}


@end

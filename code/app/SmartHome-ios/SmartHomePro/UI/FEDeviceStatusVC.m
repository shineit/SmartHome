//
//  FEDeviceStatusVC.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEDeviceStatusVC.h"
#import "FECompany.h"
#import "FEFireAlarmByCidRequest.h"
#import "FEFireAlarmResponse.h"
#import "FEMemoryCache.h"
#import "FEWebServiceManager.h"
#import "FEDeviceDetailVC.h"

@interface FEDeviceStatusVC (){
    NSMutableArray *_dataSource;
    
}

@end

@implementation FEDeviceStatusVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"设备状态";
    _dataSource = [NSMutableArray new];
    [self requestAlarm];
}

-(void)requestAlarm{
    if (self.company) {
        FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"alarmKind" value:@"1"];
        FEFireAlarmByCidRequest *rdata = [[FEFireAlarmByCidRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID comanyId:self.company.companyID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filterList:@[attr]];
        __weak typeof(self) weakself = self;
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEFireAlarmResponse class] response:^(NSError *error, id response) {
            FEFireAlarmResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                [_dataSource addObjectsFromArray:rsp.fireAlarmList];
                [weakself.tableView reloadData];
            }
        }];
    }
}


#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    FEFireAlarm *fireAlarm = _dataSource[indexPath.row];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"deviceStatusCell" forIndexPath:indexPath];
    cell.textLabel.text = fireAlarm.alarmTypeName;
    cell.detailTextLabel.text = fireAlarm.locationDesp;
    
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _dataSource.count;
}

#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    FEFireAlarm *alarm = _dataSource[indexPath.row];
    if (alarm.alarmKind.integerValue == 1) {
        [self performSegueWithIdentifier:@"toDeviceDetailSegue" sender:alarm];
    }
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
    FEDeviceDetailVC *vc = segue.destinationViewController;
    vc.company = self.company;
    vc.device = sender;
}


@end

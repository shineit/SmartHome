//
//  FECloudSafeVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECloudSafeVC.h"
#import "FEControlView.h"
#import "FECloudSafeTableCell.h"
#import "FEDeviceWarringSettingVC.h"
#import "FEWebServiceManager.h"
#import "FESensorListRequest.h"
#import "FESensorListResponse.h"
#import "FESensorBatchEnableRequest.h"
#import "FESensorBatchDisableRequest.h"
#import "FESensor.h"
#import "AppDelegate.h"
#import "FECoreDataHandler.h"
#import "FESensor.h"
#import "CDUser.h"

@interface FECloudSafeVC ()<UITableViewDelegate,UITableViewDataSource,FEControlViewDelegate>

@property (nonatomic, strong) UITableView *deviceTable;
@property (nonatomic, strong) NSMutableArray *deviceList;

@end

@implementation FECloudSafeVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"CLOUD_SAFE");
        if (SYSTEM_VERSION_UP7) {
            UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:FEString(@"CLOUD_SAFE") image:[UIImage imageNamed:@"tabbar_safe"] selectedImage:nil];
            self.tabBarItem = tabitem;
        }else{
            [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"tabbar_safe_select"] withFinishedUnselectedImage:[UIImage imageNamed:@"tabbar_safe"]];
        }
        
        _deviceList = [NSMutableArray new];//[NSMutableArray arrayWithArray:@[@[@"烟雾报警器1",@"烟雾报警器2"],@[@"温度报警器1",@"温度报警器2",@"温度报警器3"]]];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
    [self requestSensor];
    
}

-(void)initUI{
    [self loadRightCustomButtonItemWithTitle:FEString(@"SEARCH") image:nil];
    
    FEControlView *cview = [[FEControlView alloc] initWithFrame:CGRectMake(0, 0, self.view.frame.size.width, 40)];
    cview.delegate = self;
    [self.view addSubview:cview];
    _deviceTable = [[UITableView alloc] initWithFrame:CGRectMake(0, cview.frame.origin.y + cview.frame.size.height, self.view.bounds.size.width, self.view.bounds.size.height - cview.frame.size.height) style:UITableViewStylePlain];
    _deviceTable.dataSource = self;
    _deviceTable.delegate = self;
    _deviceTable.tableFooterView = [[UIView alloc] initWithFrame:CGRectZero];
    _deviceTable.tableHeaderView = [[UIView alloc] initWithFrame:CGRectZero];
    _deviceTable.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    
    [self.view addSubview:_deviceTable];
}

-(void)rightbarpressed:(UIButton *)button{
    NSLog(@"search!");
}

-(void)requestSensor{
    [self displayHUD:FEString(@"LOADING...")];
    FEPage *page = [[FEPage alloc] initWithPageSize:0 currentPage:0 count:0];
    FESensorListRequest *request = [[FESensorListRequest alloc] initWithUserID:FELoginUser.userid page:page attributes:nil];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] sensorList:request response:^(NSError *error, FESensorListResponse *response) {
        [weakself hideHUD:YES];
        if (!error && response.result.errorCode.integerValue == 0) {
            [weakself.deviceList removeAllObjects];
            if (response.sensorList.count) {
                [weakself.deviceList addObject:response.sensorList];
            }
            [weakself.deviceTable reloadData];
        }
    }];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    FECloudSafeTableCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[FECloudSafeTableCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
        
    }
    
    FESensor *sensor = _deviceList[indexPath.section][indexPath.row];
    [cell configWithSensor:sensor];
    return cell;
    
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [(NSArray *)_deviceList[section] count];
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return _deviceList.count;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section{
    return @"卧室";
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 30;
}


//-(NSInteger)tableView:(UITableView *)tableView sectionForSectionIndexTitle:(NSString *)title atIndex:(NSInteger)index{
//    
//}
#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    FEDeviceWarringSettingVC *dvc = [[FEDeviceWarringSettingVC alloc] initWithSensor:_deviceList[indexPath.section][indexPath.row]];
    dvc.hidesBottomBarWhenPushed = YES;
//    dvc.title = _deviceList[indexPath.section][indexPath.row];
    [self.navigationController pushViewController:dvc animated:YES];
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

#pragma mark - FEContrilViewDelegate
-(void)controlViewDidSelectAllOpen:(FEControlView *)cview{
    [self displayHUD:FEString(@"LOADING...")];
    FESensorBatchEnableRequest *edata = [[FESensorBatchEnableRequest alloc] initWithSensorList:self.deviceList];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] SensorBatchEnable:edata response:^(NSError *error, FEBaseResponse *response) {
        [weakself hideHUD:YES];
        if (!error && !response.result.errorCode.integerValue == 0) {
            NSLog(@"all opened!");
        }
    }];
}

-(void)controlViewDidSelectAllClose:(FEControlView *)cview{
    [self displayHUD:FEString(@"LOADING...")];
    FESensorBatchDisableRequest *sdata = [[FESensorBatchDisableRequest alloc] initWithSensorList:self.deviceList];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] SensorBatchDisable:sdata response:^(NSError *error, FEBaseResponse *response) {
        [weakself hideHUD:YES];
        if (!error && response.result.errorCode.integerValue == 0) {
            NSLog(@"ALL close!");
        }
    }];
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end

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
#import "FEUserMarkResponse.h"
#import "FEMarkRequest.h"
#import "FEDevicesCache.h"

//DISCRETE_SENSOR(0,"告警类"),
//CONTIUOUS_SENSOR(1,"模拟类"),
//CTRL_SENSOR(2,"控制类");

//typedef enum : NSUInteger {
//    DISCRETE_SENSOR = 0,
//    CONTIUOUS_SENSOR,
//    CTRL_SENSOR,
//} SENSOR_TYPE;

@interface FECloudSafeVC ()<UITableViewDelegate,UITableViewDataSource,FEControlViewDelegate,FECloudSafeTableCellDelegate,FEDeviceWarringSettingVCDelegate>

@property (nonatomic, strong) UITableView *deviceTable;
@property (nonatomic, strong) NSArray *deviceList;
@property (nonatomic, strong) UISearchBar *searchBar;
@property (nonatomic, strong) NSArray *marklist;
@property (nonatomic, strong) NSArray *alldevices;

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
        
        _deviceList = [NSMutableArray new];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    if (![FEDevicesCache sharedInstance].getAlldevices) {
        [self requestSensor];
    }else{
        self.deviceList = [[FEDevicesCache sharedInstance] getFilterSensors];;
    }
    
    [self initUI];
    [self requestMarks];
    
}

-(void)initUI{
//    [self loadRightCustomButtonItemWithTitle:FEString(@"SEARCH") image:nil];
//    _searchBar = [[UISearchBar alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 44)];
//    [self.view addSubview:_searchBar];
    
    
//    FEControlView *cview = [[FEControlView alloc] initWithFrame:CGRectMake(0, _searchBar.frame.origin.y + _searchBar.bounds.size.height, self.view.frame.size.width, 40)];
//    cview.delegate = self;
//    [self.view addSubview:cview];
    _deviceTable = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, self.view.bounds.size.height) style:UITableViewStylePlain];
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
//    [self displayHUD:FEString(@"LOADING...")];
    FEPage *page = [[FEPage alloc] initWithPageSize:0 currentPage:0 count:0];
    FESensorListRequest *request = [[FESensorListRequest alloc] initWithUserID:FELoginUser.userid page:page attributes:nil];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] sensorList:request response:^(NSError *error, FESensorListResponse *response) {
//        [weakself hideHUD:YES];
        if (!error && response.result.errorCode.integerValue == 0) {
//            self.alldevices = response.sensorList;
            [[FEDevicesCache sharedInstance] putDevices:response.sensorList];
            self.deviceList = [[FEDevicesCache sharedInstance] getFilterSensors];
            
            [weakself.deviceTable reloadData];
        }
    }];
}

-(NSArray *)filterSensor:(NSArray *)allsensor{
    NSMutableArray *sensors = [NSMutableArray array];
    NSArray *sensorList = [allsensor filteredArrayUsingPredicate:[NSPredicate predicateWithFormat:@"SELF.sensorKind == %d || SELF.sensorKind == %d",CONTIUOUS_SENSOR,DISCRETE_SENSOR]];
    if (sensorList.count) {
        NSArray *allmarks = [sensorList valueForKey:@"mark"];
        NSSet *set = [NSSet setWithArray:allmarks];
        NSArray *marks = set.allObjects;
        
        for (NSString *mark in marks) {
            [sensors addObject:@{__SENSOR_MARK:mark,__SENSOR_LIST:[sensorList filteredArrayUsingPredicate:[NSPredicate predicateWithFormat:@"SELF.mark == %@",mark]]}];
        }
    }
    return sensors;
}

-(void)requestMarks{
    __weak typeof(self) weakself = self;
    FEPage *page = [[FEPage alloc] initWithPageSize:0 currentPage:0 count:0];
    FEMarkRequest *rdata = [[FEMarkRequest alloc] initWithUserid:FELoginUser.userid page:page];
    [[FEWebServiceManager sharedInstance] markList:rdata response:^(NSError *error, FEUserMarkResponse *response) {
        if (!error && response.result.errorCode.integerValue == 0) {
            weakself.marklist = response.markList;
        }
    }];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    FECloudSafeTableCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[FECloudSafeTableCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
        cell.delegate = self;
        
    }
    
    FESensor *sensor = _deviceList[indexPath.section][__SENSOR_LIST][indexPath.row];
    [cell configWithSensor:sensor];
    return cell;
    
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [(NSArray *)_deviceList[section][__SENSOR_LIST] count];
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return _deviceList.count;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section{
    return self.deviceList[section][__SENSOR_MARK];
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 30;
}


//-(NSInteger)tableView:(UITableView *)tableView sectionForSectionIndexTitle:(NSString *)title atIndex:(NSInteger)index{
//    
//}
#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    FEDeviceWarringSettingVC *dvc = [[FEDeviceWarringSettingVC alloc] initWithSensor:_deviceList[indexPath.section][__SENSOR_LIST][indexPath.row] markList:self.marklist];
    dvc.delegate = self;
    dvc.hidesBottomBarWhenPushed = YES;
//    dvc.title = _deviceList[indexPath.section][indexPath.row];
    [self.navigationController pushViewController:dvc animated:YES];
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

#pragma mark - FECloudSafeTableCellDelegate
-(void)switchStatusDidChange:(FECloudSafeTableCell *)cell switcher:(UISwitch *)sw1{
    [self changeSensorStatus:cell.sensor enable:sw1.on];
}

#pragma mark - FEContrilViewDelegate
-(void)controlViewDidSelectAllOpen:(FEControlView *)cview{
    [self displayHUD:FEString(@"LOADING...")];
    NSMutableArray *allsensors = [NSMutableArray array];
    for (NSDictionary *item in self.deviceList) {
        [allsensors addObjectsFromArray:item[__SENSOR_LIST]];
    }
    FESensorBatchEnableRequest *edata = [[FESensorBatchEnableRequest alloc] initWithSensorList:allsensors];
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
    NSMutableArray *allsensors = [NSMutableArray array];
    for (NSDictionary *item in self.deviceList) {
        [allsensors addObjectsFromArray:item[__SENSOR_LIST]];
    }
    FESensorBatchDisableRequest *sdata = [[FESensorBatchDisableRequest alloc] initWithSensorList:allsensors];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] SensorBatchDisable:sdata response:^(NSError *error, FEBaseResponse *response) {
        [weakself hideHUD:YES];
        if (!error && response.result.errorCode.integerValue == 0) {
            NSLog(@"ALL close!");
        }
    }];
}

-(void)changeSensorStatus:(FESensor *)sensor enable:(BOOL)enable{
    [self displayHUD:FEString(@"LOADING...")];
    if (enable) {
        FESensorBatchEnableRequest *edata = [[FESensorBatchEnableRequest alloc] initWithSensorList:@[sensor]];
        __weak typeof(self) weakself = self;
        [[FEWebServiceManager sharedInstance] SensorBatchEnable:edata response:^(NSError *error, FEBaseResponse *response) {
            [weakself hideHUD:YES];
            if (!error && !response.result.errorCode.integerValue == 0) {
                NSLog(@"opened!");
            }
        }];
    }else{
        FESensorBatchDisableRequest *sdata = [[FESensorBatchDisableRequest alloc] initWithSensorList:@[sensor]];
        __weak typeof(self) weakself = self;
        [[FEWebServiceManager sharedInstance] SensorBatchDisable:sdata response:^(NSError *error, FEBaseResponse *response) {
            [weakself hideHUD:YES];
            if (!error && response.result.errorCode.integerValue == 0) {
                NSLog(@"close!");
            }
        }];
    }
}

#pragma mark - FEDeviceWarringSettingVCDelegate
-(void)sensorDidConfig{
    self.deviceList = [[FEDevicesCache sharedInstance] getFilterSensors];
    [self.deviceTable reloadData];
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

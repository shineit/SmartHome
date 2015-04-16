//
//  FEDeviceDetailVC.m
//  SmartHome
//
//  Created by Seven on 15-4-7.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEDeviceDetailVC.h"
#import "FEFireAlarm.h"
#import "NSDate+Formatter.h"
#import "FECompany.h"
#import "FEPlanVC.h"
#import "FEButton.h"
#import <ZBUtilities/UIImage+LogN.h>
#import "UIColor+Theme.h"

@interface FEDeviceDetailVC ()
@property (strong, nonatomic) IBOutlet UILabel *machineIDLabel;
@property (strong, nonatomic) IBOutlet UILabel *loopIDLabel;
@property (strong, nonatomic) IBOutlet UILabel *codeID;
@property (strong, nonatomic) IBOutlet UILabel *alarmTimeLabel;
@property (strong, nonatomic) IBOutlet UILabel *sensorTypeName;
@property (strong, nonatomic) IBOutlet UILabel *locationDesp;
@property (strong, nonatomic) IBOutlet UILabel *alarmTypeName;
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;
@property (strong, nonatomic) IBOutlet FEButton *locationButton;

@end

@implementation FEDeviceDetailVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"设备信息";
    self.view.backgroundColor = [UIColor whiteColor];
    [self.locationButton setBackgroundImage:[UIImage imageFromColor:[UIColor ThemeColor]] forState:UIControlStateNormal];
    [self refreshUI];
}

-(void)refreshUI{
    
    self.titleLabel.text = self.company.companyName;
    self.machineIDLabel.text = self.device.machineID.stringValue;
    self.loopIDLabel.text = self.device.loopID.stringValue;
    self.codeID.text = self.device.codeID.stringValue;
    NSDate *date = [NSDate dateWithTimeIntervalSince1970:self.device.alarmTime.integerValue / 1000];
    
    self.alarmTimeLabel.text = [date defaultFormat];//self.device.alarmTime.stringValue;
    self.sensorTypeName.text = self.device.sensorTypeName;
    self.locationDesp.text = self.device.locationDesp;
    self.alarmTypeName.text = self.device.alarmTypeName;
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
    FEPlanVC *vc = segue.destinationViewController;
    vc.alarm = self.device;
}


@end

//
//  FEWarringResponse.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEWarringResponse.h"
#import "FEDeviceInfoView.h"
#import "FEDeviceInfoInputView.h"
#import "FEAlarm.h"

@interface FEWarringResponse (){
    FEAlarm *_alarm;
    NSArray *_alarmDevices;
}

@property (nonatomic, strong) FEDeviceInfoView *infoView;
@property (nonatomic, strong) FEDeviceInfoInputView *infonInput;

@end

@implementation FEWarringResponse

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"CLOUD_SAFE");
    }
    return self;
}

-(id)initWithAlarm:(FEAlarm *)alarm{
    self = [super init];
    if (self) {
        self.title = FEString(@"CLOUD_SAFE");
        _alarm = alarm;
        _alarmDevices = [NSArray arrayWithObjects:FEString(@"CONCENTRATOR_ALARM"),FEString(@"HOME_SENSOR"),FEString(@"FIRE_SENSOR"), nil];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
}

-(void)initUI{
    _infoView = [[FEDeviceInfoView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 150)];
    _infoView.controlPoint.text = [NSString stringWithFormat:@"%@",_alarm.objID];
    _infoView.deviceNumber.text = [NSString stringWithFormat:@"%@",_alarm.id];
    _infoView.deviceType.text = _alarmDevices[_alarm.objType.integerValue];
    [self.view addSubview:_infoView];
    
    _infonInput = [[FEDeviceInfoInputView alloc] initWithFrame:CGRectMake(0, _infoView.bounds.size.height, self.view.bounds.size.width, 150)];
    [self.view addSubview:_infonInput];
    
    UIButton *cleanBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    [cleanBtn addTarget:self action:@selector(cleanwarring:) forControlEvents:UIControlEventTouchUpInside];
    cleanBtn.frame = CGRectMake(40, _infonInput.frame.origin.y + _infonInput.frame.size.height + 20, 240, 40);
    [cleanBtn setTitle:FEString(@"CLEAN_WARRING") forState:UIControlStateNormal];
    [self.view addSubview:cleanBtn];
}

//
-(void)cleanwarring:(UIButton *)btn{
    NSLog(@"clean!");
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

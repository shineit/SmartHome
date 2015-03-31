//
//  FEWarringResponse.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEWarringDetailVC.h"
#import "FEDeviceInfoView.h"
#import "FEDeviceInfoInputView.h"
#import "FEAlarm.h"
#import "FEEnumString.h"
#import "FECommonDefine.h"

@interface FEWarringDetailVC (){
    FEAlarm *_alarm;
    NSArray *_alarmDevices;
}

@property (nonatomic, strong) FEDeviceInfoView *infoView;
@property (nonatomic, strong) FEDeviceInfoInputView *infonInput;
@property (nonatomic, strong) UIScrollView *scrollContent;

@end

@implementation FEWarringDetailVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = kString(@"CLOUD_SAFE");
    }
    return self;
}

-(id)initWithAlarm:(FEAlarm *)alarm{
    self = [super init];
    if (self) {
        self.title = kString(@"CLOUD_SAFE");
        _alarm = alarm;
        _alarmDevices = [NSArray arrayWithObjects:kString(@"CONCENTRATOR_ALARM"),kString(@"HOME_SENSOR"),kString(@"FIRE_SENSOR"), nil];
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
    
    UIScrollView *scrollview = [[UIScrollView alloc] initWithFrame:self.view.bounds];
    scrollview.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    scrollview.contentSize = CGSizeMake(self.view.bounds.size.width, self.view.bounds.size.height);
    self.scrollContent = scrollview;
    [self.view addSubview:scrollview];
    
    _infoView = [[FEDeviceInfoView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 150)];
    _infoView.controlPoint.text = [NSString stringWithFormat:@"%@",_alarm.objID];
    _infoView.deviceNumber.text = [NSString stringWithFormat:@"%@",_alarm.id];
    _infoView.deviceType.text = _alarmDevices[_alarm.objType.integerValue];
    [scrollview addSubview:_infoView];
    
    _infonInput = [[FEDeviceInfoInputView alloc] initWithFrame:CGRectMake(0, _infoView.bounds.size.height, self.view.bounds.size.width, 150)];
    [scrollview addSubview:_infonInput];
    
    UIView *typeview = [[UIView alloc] initWithFrame:CGRectMake(0, _infonInput.frame.origin.y + _infonInput.bounds.size.height, self.view.bounds.size.width, 40)];
    typeview.backgroundColor = [UIColor whiteColor];
    [scrollview addSubview:typeview];
    
    FELabel *label = [[FELabel alloc] initWithFrame:CGRectMake(10, 10, 60, 20)];
    label.textColor = [UIColor ThemeColor];
    label.text = kString(@"ALARM_ALARM");
    [typeview addSubview:label];
    
    FELabel *labelAlarmType = [[FELabel alloc] initWithFrame:CGRectMake(75, 10, 100, 20)];
    labelAlarmType.textColor = [UIColor ThemeColor];
    labelAlarmType.text = [FEEnumString alarmType:_alarm.alarmType];
    [typeview addSubview:labelAlarmType];
    
    FEButton *cleanBtn = [FEButton buttonWithType:UIButtonTypeCustom];
    [cleanBtn addTarget:self action:@selector(cleanwarring:) forControlEvents:UIControlEventTouchUpInside];
    cleanBtn.frame = CGRectMake(40, typeview.frame.origin.y + typeview.frame.size.height + 20, 240, 40);
    [cleanBtn setTitle:kString(@"ALARM_CLEAN_WARRING") forState:UIControlStateNormal];
    [scrollview addSubview:cleanBtn];
    
    
}

#pragma override
-(void)keyboardWillHide:(CGRect)newRect duration:(NSTimeInterval)duration{
    self.scrollContent.frame = self.view.bounds;
}

-(void)keyboardWillShow:(CGRect)newRect duration:(NSTimeInterval)duration{
    [UIView animateWithDuration:duration animations:^(void){
        CGRect frame = self.view.bounds;
        frame.size.height -= newRect.size.height;
        self.scrollContent.frame = frame;
    }];
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

//
//  FEDeviceControllVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDeviceControllVC.h"
#import "FEDeviceInfoView.h"
#import "FESensor.h"
#import "FEPopPickerView.h"
#import "FEUserMark.h"


@interface FEDeviceControllVC ()<FEPopPickerViewDataSource,FEPopPickerViewDelegate>

@property (nonatomic, strong) UITextView *descripTextField;
@property (nonatomic, strong) FEPopPickerView *labelPickerView;
@property (nonatomic, strong) UITextField *regionNumberTextField;
@property (nonatomic, strong) FEUserMark *selectedMark;

@end

@implementation FEDeviceControllVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

-(id)initWithSensor:(FESensor *)sensor{
    self = [super init];
    if (self) {
        _sensor = sensor;
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
    FEDeviceInfoView *infoview = [[FEDeviceInfoView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 150)];
    infoview.controlPoint.text = self.sensor.concentratorID.stringValue;
    infoview.deviceNumber.text = self.sensor.sensorID.stringValue;
    infoview.deviceType.text = self.sensor.sensorTypeName;
    [self.view addSubview:infoview];
    
    
    UIView *contentview = [[UIView alloc] initWithFrame:CGRectMake(0, infoview.bounds.size.height, self.view.bounds.size.width, 200)];
    contentview.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:contentview];
    
    CGFloat x = 10; //label 起始X
    CGFloat y = 20; //label 起始Y
    CGFloat xspace = 10; //space of item x
    CGFloat yspace = 20; //space of item y
    CGFloat lwidth = 60; //label width
    CGFloat twidth = contentview.bounds.size.width - x - xspace - lwidth - 20; //textFeild width
    CGFloat height = 30;  //item (label or textFeild height)
    
    
    FELabel *descriptor = [[FELabel alloc] initWithFrame:CGRectMake(x, y, lwidth, height)];
    descriptor.text = FEString(@"CONTROLL_DESCRIPTION");
    [contentview addSubview:descriptor];
    
    UITextView *dtextfield = [[UITextView alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y, twidth, height * 3)];
    dtextfield.font = [UIFont appFontWithSize:16];
    dtextfield.layer.borderWidth = 0.5;
    dtextfield.layer.masksToBounds = YES;
    dtextfield.layer.cornerRadius = 3;
    dtextfield.text = self.sensor.descriptions;
    [contentview addSubview:dtextfield];
    self.descripTextField = dtextfield;
    
    FELabel *llabel = [[FELabel alloc] initWithFrame:CGRectMake(x, dtextfield.frame.origin.y + dtextfield.bounds.size.height + yspace, lwidth, height)];
    llabel.text = FEString(@"CONTROLL_LABEL");
    [contentview addSubview:llabel];
    
    _labelPickerView = [[FEPopPickerView alloc] initWithFrame:CGRectMake(x + lwidth + xspace, llabel.frame.origin.y, twidth - 100, height)];
    _labelPickerView.dataSource = self;
    _labelPickerView.delegate = self;
    NSInteger index = -1;
    for (FEUserMark *mark in self.marks) {
        if ([mark.mark isEqualToString:self.sensor.mark]) {
            index = [self.marks indexOfObject:mark];
            self.selectedMark = mark;
            break;
        }
    }
    [_labelPickerView setSelected:index];
    [contentview addSubview:_labelPickerView];
    
    FEButton *add = [FEButton buttonWithType:UIButtonTypeCustom];
    add.frame = CGRectMake(x + lwidth + xspace + twidth - 90, llabel.frame.origin.y, 90, height);
    [add setTitle:FEString(@"CONTROLL_LABEL_MANAGE") forState:UIControlStateNormal];
    [contentview addSubview:add];
    
    FELabel *rlabel = [[FELabel alloc] initWithFrame:CGRectMake(x, llabel.frame.origin.y + llabel.bounds.size.height + yspace, lwidth, height)];
    rlabel.text = FEString(@"CONTROLL_REGION");
    [contentview addSubview:rlabel];
    
    _regionNumberTextField = [[UITextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, llabel.frame.origin.y + llabel.bounds.size.height + yspace, twidth, height)];
    _regionNumberTextField.borderStyle = UITextBorderStyleRoundedRect;
    [contentview addSubview:_regionNumberTextField];
    
    contentview.frame = CGRectMake(0, infoview.bounds.size.height, self.view.bounds.size.width, _regionNumberTextField.frame.origin.y + _regionNumberTextField.bounds.size.height + yspace);
    
    FEButton *config = [FEButton buttonWithType:UIButtonTypeCustom];
    config.frame = CGRectMake((self.view.bounds.size.width - 220) / 2.0f, contentview.bounds.size.height + contentview.frame.origin.y + 20, 220, 40);
    [config setTitle:FEString(@"CONTROLL_CONFIGURE") forState:UIControlStateNormal];
    [config addTarget:self action:@selector(config:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:config];
    
}

#pragma mark - FEPopPickerViewDataSource
-(NSString *)popPickerTitleAtIndex:(NSInteger)index{
    return ((FEUserMark *)self.marks[index]).mark;
}

-(NSInteger)popPickerItemNumber{
    return self.marks.count;
}


-(void)config:(UIButton *)button{
    NSLog(@"configuretion");
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

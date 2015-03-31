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
#import "FESensorSetRequest.h"
#import "FESensorSetResponse.h"
#import "FEWebServiceManager.h"
#import "FEUserMarkManagerVC.h"
#import "AppDelegate.h"
#import "FECommonDefine.h"
#import <SSCommon-Utilities/FEPopPickerView.h>


@interface FEDeviceControllVC ()<FEPopPickerViewDataSource>

@property (nonatomic, strong) UITextView *descripTextField;
//@property (nonatomic, strong) FEPopPickerView *labelPickerView;
@property (nonatomic, strong) UITextField *regionNumberTextField;
@property (nonatomic, strong) FEUserMark *selectedMark;
@property (nonatomic, strong) UIScrollView *scrollView;
@property (nonatomic, strong) UIButton *markButton;
@property (nonatomic, strong) NSString *markName;

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
    self.markName = self.sensor.mark;
    [self initUI];
}

-(void)initUI{
    
    UIScrollView *scrollview = [[UIScrollView alloc] initWithFrame:self.view.bounds];
    scrollview.userInteractionEnabled = YES;
    [self.view addSubview:scrollview];
    self.scrollView = scrollview;
    
    FEDeviceInfoView *infoview = [[FEDeviceInfoView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 150)];
    infoview.controlPoint.text = self.sensor.concentratorID.stringValue;
    infoview.deviceNumber.text = self.sensor.sensorID.stringValue;
    infoview.deviceType.text = self.sensor.sensorTypeName;
    [self.scrollView addSubview:infoview];
    
    
    UIView *contentview = [[UIView alloc] initWithFrame:CGRectMake(0, infoview.bounds.size.height, self.view.bounds.size.width, 200)];
    contentview.backgroundColor = [UIColor whiteColor];
    [self.scrollView addSubview:contentview];
    
    CGFloat x = 10; //label 起始X
    CGFloat y = 20; //label 起始Y
    CGFloat xspace = 10; //space of item x
    CGFloat yspace = 20; //space of item y
    CGFloat lwidth = 60; //label width
    CGFloat twidth = contentview.bounds.size.width - x - xspace - lwidth - 20; //textFeild width
    CGFloat height = 30;  //item (label or textFeild height)
    
    
    FELabel *descriptor = [[FELabel alloc] initWithFrame:CGRectMake(x, y, lwidth, height)];
    descriptor.text = kString(@"CONTROLL_DESCRIPTION");
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
    llabel.text = kString(@"CONTROLL_LABEL");
    [contentview addSubview:llabel];
    
    UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
    btn.frame = CGRectMake(x + lwidth + xspace, llabel.frame.origin.y, twidth - 100, height);
    [btn addTarget:self action:@selector(selectMark) forControlEvents:UIControlEventTouchUpInside];
    [btn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [btn setTitle:@"未命名" forState:UIControlStateNormal];
    [contentview addSubview:btn];
    [btn setBackgroundImage:[UIImage imageFromColor:[UIColor lightGrayColor]] forState:UIControlStateNormal];
    self.markButton = btn;
//    _labelPickerView = [[FEPopPickerView alloc] initWithFrame:CGRectMake(x + lwidth + xspace, llabel.frame.origin.y, twidth - 100, height)];
//    _labelPickerView.dataSource = self;
//    _labelPickerView.delegate = self;
//    NSInteger index = -1;
//    for (FEUserMark *mark in self.marks) {
//        if ([mark.mark isEqualToString:self.sensor.mark]) {
//            index = [self.marks indexOfObject:mark];
//            self.selectedMark = mark;
//            break;
//        }
//    }
//    [_labelPickerView setSelected:index];
//    [contentview addSubview:_labelPickerView];
    
    FEButton *add = [FEButton buttonWithType:UIButtonTypeCustom];
    add.frame = CGRectMake(x + lwidth + xspace + twidth - 90, llabel.frame.origin.y, 90, height);
    [add addTarget:self action:@selector(manageMark:) forControlEvents:UIControlEventTouchUpInside];
    [add setTitle:kString(@"CONTROLL_LABEL_MANAGE") forState:UIControlStateNormal];
    [contentview addSubview:add];
    
    FELabel *rlabel = [[FELabel alloc] initWithFrame:CGRectMake(x, llabel.frame.origin.y + llabel.bounds.size.height + yspace, lwidth, height)];
    rlabel.text = kString(@"CONTROLL_REGION");
    [contentview addSubview:rlabel];
    
    _regionNumberTextField = [[UITextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, llabel.frame.origin.y + llabel.bounds.size.height + yspace, twidth, height)];
    _regionNumberTextField.borderStyle = UITextBorderStyleRoundedRect;
    [contentview addSubview:_regionNumberTextField];
    
    contentview.frame = CGRectMake(0, infoview.bounds.size.height, self.view.bounds.size.width, _regionNumberTextField.frame.origin.y + _regionNumberTextField.bounds.size.height + yspace);
    
    FEButton *config = [FEButton buttonWithType:UIButtonTypeCustom];
    config.frame = CGRectMake((self.view.bounds.size.width - 220) / 2.0f, contentview.bounds.size.height + contentview.frame.origin.y + 20, 220, 40);
    [config setTitle:kString(@"CONTROLL_CONFIGURE") forState:UIControlStateNormal];
    [config addTarget:self action:@selector(config:) forControlEvents:UIControlEventTouchUpInside];
    [self.scrollView addSubview:config];
    
    scrollview.contentSize = CGSizeMake(scrollview.bounds.size.width, config.frame.origin.y + config.bounds.size.height + 20);
    scrollview.autoresizingMask = UIViewAutoresizingFlexibleHeight;
}

-(void)selectMark{
    FEPopPickerView *popPicker = [[FEPopPickerView alloc] initFromView:[[AppDelegate sharedDelegate].window viewWithTag:0]];
    popPicker.tlabel.text = @"标签";
    popPicker.titleColor = [UIColor ThemeColor];
    NSInteger index = -1;
    for (FEUserMark *mark in self.marks) {
        if ([self.markName isEqualToString:mark.mark]) {
            index = [self.marks indexOfObject:mark];
            break;
        }
    }
    popPicker.selectIndex = index;
    popPicker.dataSource = self;
    [popPicker show];
}

#pragma mark - FEPopPickerView
-(NSInteger)numberInPicker:(FEPopPickerView *)picker{
    return self.marks.count;
}
-(NSString *)picker:(FEPopPickerView *)picker titleAtIndex:(NSInteger)index{
    return ((FEUserMark *)self.marks[index]).mark;
}

-(void)picker:(FEPopPickerView *)picker didSelectAtIndex:(NSInteger)index{
    self.markName = ((FEUserMark *)self.marks[index]).mark;
    [self.markButton setTitle:self.markName forState:UIControlStateNormal];
}

//#pragma mark - FEPopPickerViewDataSource
//-(NSString *)popPickerTitleAtIndex:(NSInteger)index{
//    return ((FEUserMark *)self.marks[index]).mark;
//}
//
//-(NSInteger)popPickerItemNumber{
//    return self.marks.count;
//}
//
-(void)manageMark:(UIButton *)button{
    FEUserMarkManagerVC *manager = [FEUserMarkManagerVC new];
    [self.navigationController pushViewController:manager animated:YES];
}


-(void)config:(UIButton *)button{
    
    [self displayHUD:kString(@"LOADING...")];
    __weak typeof(self) weakself = self;
    FESensor *sensor = [self.sensor copy];
    sensor.mark = self.markName;
    sensor.descriptions = self.descripTextField.text;
    FESensorSetRequest *rdata = [[FESensorSetRequest alloc] initWithCommond:@(0) sensor:sensor];
    [[FEWebServiceManager sharedInstance] sensorSet:rdata response:^(NSError *error, FESensorSetResponse *response) {
        if (!error && response.result.errorCode.integerValue == 0) {
            weakself.sensor.mark = sensor.mark; //setValue:weakself.selectedMark.mark forKey:@"mark"];
            weakself.sensor.descriptions = sensor.descriptions;// setValue:self.descripTextField.text forKey:@"descriptions"];
            if ([weakself.delegate respondsToSelector:@selector(sensorDidConfig)]) {
                [weakself.delegate sensorDidConfig];
            }
            [weakself.navigationController popViewControllerAnimated:YES];
            
        }
        [weakself hideHUD:YES];
    }];
//    NSLog(@"configuretion");
}

#pragma override
-(void)keyboardWillHide:(CGRect)newRect duration:(NSTimeInterval)duration{
    self.scrollView.frame = self.view.bounds;
}

-(void)keyboardWillShow:(CGRect)newRect duration:(NSTimeInterval)duration{
    [UIView animateWithDuration:duration animations:^(void){
        CGRect frame = self.view.bounds;
        frame.size.height -= newRect.size.height;
        self.scrollView.frame = frame;
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

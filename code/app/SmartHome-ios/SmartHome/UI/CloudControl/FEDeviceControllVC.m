//
//  FEDeviceControllVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDeviceControllVC.h"
#import "FEDeviceInfoView.h"


@interface FEDeviceControllVC ()

@property (nonatomic, strong) UITextField *descripTextField;
@property (nonatomic, strong) UITextField *labelTextField;
@property (nonatomic, strong) UITextField *regionNumberTextField;

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

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    [self initUI];
}

-(void)initUI{
    FEDeviceInfoView *infoview = [[FEDeviceInfoView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 150)];
    [self.view addSubview:infoview];
    
    UIView *contentview = [[UIView alloc] initWithFrame:CGRectMake(0, infoview.bounds.size.height, self.view.bounds.size.width, 200)];
    contentview.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:contentview];
    
    CGFloat x = 10; //label 起始X
    CGFloat y = 20; //label 起始Y
    CGFloat xspace = 10; //space of item x
    CGFloat yspace = 20; //space of item y
    CGFloat lwidth = 60; //label width
    CGFloat twidth = 200; //textFeild width
    CGFloat height = 30;  //item (label or textFeild height)
    
    
    FELabel *descriptor = [[FELabel alloc] initWithFrame:CGRectMake(x, y, lwidth, height)];
    descriptor.text = FEString(@"DESCRIPTION");
    [contentview addSubview:descriptor];
    
    UITextField *dtextfield = [[UITextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y, twidth, height)];
    dtextfield.placeholder = FEString(@"INPUT_DESCRIPTION");
    dtextfield.borderStyle = UITextBorderStyleRoundedRect;
    [contentview addSubview:dtextfield];
    self.descripTextField = dtextfield;
    
    FELabel *llabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + height + yspace, lwidth, height)];
    llabel.text = FEString(@"LABEL");
    [contentview addSubview:llabel];
    
    _labelTextField = [[UITextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + height + yspace, twidth - 60, height)];;
    _labelTextField.borderStyle = UITextBorderStyleRoundedRect;
    [contentview addSubview:_labelTextField];
    
    UIButton *add = [UIButton buttonWithType:UIButtonTypeCustom];
    add.frame = CGRectMake(x + lwidth + xspace + twidth - 50, y + height + yspace, 50, height);
    [add setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [add setTitle:FEString(@"ADD") forState:UIControlStateNormal];
    [contentview addSubview:add];
    
    FELabel *rlabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + 2 * (height + yspace), lwidth, height)];
    rlabel.text = FEString(@"REGION");
    [contentview addSubview:rlabel];
    
    _regionNumberTextField = [[UITextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + 2 * (height + yspace), twidth, height)];
    _regionNumberTextField.borderStyle = UITextBorderStyleRoundedRect;
    [contentview addSubview:_regionNumberTextField];
    
    UIButton *config = [UIButton buttonWithType:UIButtonTypeCustom];
    config.frame = CGRectMake(50, contentview.bounds.size.height + contentview.frame.origin.y + 20, 220, 30);
    [config setTitle:FEString(@"CONFIGURE") forState:UIControlStateNormal];
    [config addTarget:self action:@selector(config:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:config];
    
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

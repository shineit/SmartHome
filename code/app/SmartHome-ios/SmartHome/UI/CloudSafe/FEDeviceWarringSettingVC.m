//
//  FEDeviceWarringSettingVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDeviceWarringSettingVC.h"
#import "FEDeviceInfoView.h"

@interface FEDeviceWarringSettingVC ()

@end

@implementation FEDeviceWarringSettingVC

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
    FEDeviceInfoView *dview = [[FEDeviceInfoView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 140)];
    [self.view addSubview:dview];
    
    UIView *contentview = [[UIView alloc] initWithFrame:CGRectMake(0, dview.bounds.size.height, self.view.bounds.size.width, 260)];
    contentview.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:contentview];
    
    
    CGFloat x = 10; //label 起始X
    CGFloat y = 20; //label 起始Y
    CGFloat xspace = 10; //space of item x
    CGFloat yspace = 20; //space of item y
    CGFloat lwidth = 60; //label width
    CGFloat twidth = 200; //textFeild width
    CGFloat height = 30;  //item (label or textFeild height)
    
    
    FELabel *dlabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y, lwidth, height)];
    dlabel.text = FEString(@"DESCRIPETION");
    [contentview addSubview:dlabel];
    
    FETextField *dtextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y, twidth, height)];
    [contentview addSubview:dtextFeild];
    
    FELabel *elabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + height + yspace, lwidth, height)];
    elabel.text = FEString(@"EARLY_WARRING");
    [contentview addSubview:elabel];
    
    FETextField *etextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + height + yspace, twidth, height)];
    [contentview addSubview:etextFeild];
    
    FELabel *vlabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + 2 * (height + yspace), lwidth, height)];
    vlabel.text = FEString(@"FIRE_VALUE");
    [contentview addSubview:vlabel];
    
    FETextField *vtextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + 2 * (height + yspace), twidth, height)];
    [contentview addSubview:vtextFeild];
    
    FELabel *llabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + 3 * (height + yspace), lwidth, height)];
    llabel.text = FEString(@"LABEL");
    [contentview addSubview:llabel];
    
    FETextField *ltextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + 3 * (height + yspace), twidth - 60, height)];
    [contentview addSubview:ltextFeild];
    
    UIButton *add = [UIButton buttonWithType:UIButtonTypeCustom];
    add.frame = CGRectMake(x + lwidth + xspace + twidth - 50, y + 3 * (height + yspace), 50, height);
    [add setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [add setTitle:FEString(@"ADD") forState:UIControlStateNormal];
    [contentview addSubview:add];
    
    FELabel *clabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + 4 * (height + yspace), lwidth, height)];
    clabel.text = FEString(@"CONTROL");
    [contentview addSubview:clabel];
    
    FETextField *ctextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + 4 * (height + yspace), twidth, height)];
    [contentview addSubview:ctextFeild];
    
    UIButton *configbutton = [UIButton buttonWithType:UIButtonTypeCustom];
    configbutton.autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleBottomMargin;
    configbutton.frame = CGRectMake(20, contentview.frame.origin.y + contentview.bounds.size.height + 20, 100, 30);
    [configbutton setTitle:FEString(@"CONFIG") forState:UIControlStateNormal];
    [self.view addSubview:configbutton];
    
    UIButton *monitor = [UIButton buttonWithType:UIButtonTypeCustom];
    monitor.autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleBottomMargin;
    monitor.frame = CGRectMake(self.view.bounds.size.width - 20 - 100, contentview.frame.origin.y + contentview.bounds.size.height + 20, 100, 30);
    [monitor setTitle:FEString(@"MONITOR") forState:UIControlStateNormal];
    [self.view addSubview:monitor];
    
    
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

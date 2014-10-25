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
    
    UIView *contentview = [[UIView alloc] initWithFrame:CGRectMake(0, dview.bounds.size.height, self.view.bounds.size.width, 230)];
    contentview.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:contentview];
    
    FELabel *dlabel = [[FELabel alloc] initWithFrame:CGRectMake(10, 20, 60, 30)];
    dlabel.text = FEString(@"DESCRIPETION");
    [contentview addSubview:dlabel];
    
    
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

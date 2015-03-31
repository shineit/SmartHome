//
//  FEProfileVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEProfileVC.h"
#import "CDUser.h"
#import "AppDelegate.h"
#import "FEModifyPassWord.h"
#import "FEWebServiceManager.h"
#import "FELogoutRequest.h"
#import "FESigoutResponse.h"
#import "FEDevicesCache.h"
#import "FECommonDefine.h"

@interface FEProfileVC ()

@end

@implementation FEProfileVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = kString(@"PROFILE_TITLE");
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
    
    [self loadRightCustomButtonItemWithTitle:kString(@"PRODILE_PASSWORD") image:nil];
    
    UIView *content = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 120)];
    content.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:content];
    
    CDUser *user = FELoginUser;
    
    FELabel *username = [[FELabel alloc] initWithFrame:CGRectMake(20, 30 , 60, 20)];
    username.text = kString(@"PROFILE_USERNAME");
    [content addSubview:username];
    
    FELabel *name = [[FELabel alloc] initWithFrame:CGRectMake(90, 30, 200, 20)];
    name.text = user.username;
    [content addSubview:name];
    
    FELabel *email = [[FELabel alloc] initWithFrame:CGRectMake(20, 70 + 5, 60, 20)];
    email.text = kString(@"PROFILE_EMAIL");
    [content addSubview:email];
    
    UITextField *emailText = [[UITextField alloc] initWithFrame:CGRectMake(90, 70, 200, 30)];
    emailText.borderStyle = UITextBorderStyleRoundedRect;
    [content addSubview:emailText];
    
    FEButton *mod = [FEButton buttonWithType:UIButtonTypeCustom];
    mod.frame = CGRectMake(20, content.frame.origin.y + content.bounds.size.height + 20, 280, 40);
    [mod setTitle:kString(@"PROFILE_MODIFY") forState:UIControlStateNormal];
    [self.view addSubview:mod];
    
    FEButton *logout = [FEButton buttonWithType:UIButtonTypeCustom];
    logout.frame = CGRectMake(20, mod.frame.origin.y + mod.bounds.size.height + 20, 280, 40);
    [logout setTitle:kString(@"PROFILE_LOGOUT") forState:UIControlStateNormal];
    [logout addTarget:self action:@selector(logout:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:logout];
    
}

-(void)rightbarpressed:(UIButton *)button{
    FEModifyPassWord *psw = [FEModifyPassWord new];
    [self.navigationController pushViewController:psw animated:YES];
}

-(void)logout:(UIButton *)button{
    
    [self displayHUD:kString(@"LOADING...")];
    FELogoutRequest *request = [[FELogoutRequest alloc] initWithUserName:FELoginUser.username];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] sigoutWithParam:request response:^(NSError *error, FESigoutResponse *response) {
        [weakself hideHUD:YES];
        if (!error && response.result.errorCode.integerValue == 0) {
            [FECoreData deleteCoreData:@[FELoginUser]];
            [FECoreData saveCoreData];
            [[AppDelegate sharedDelegate] loadSigin];
            [[FEDevicesCache sharedInstance] clearCache];
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

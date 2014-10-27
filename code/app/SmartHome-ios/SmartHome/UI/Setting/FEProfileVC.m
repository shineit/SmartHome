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

@interface FEProfileVC ()

@end

@implementation FEProfileVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"PROFILE");
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
    
    [self loadRightCustomButtonItemWithTitle:FEString(@"PASSWORD") image:nil];
    
    CDUser *user = FELoginUser;
    
    FELabel *username = [[FELabel alloc] initWithFrame:CGRectMake(20, 30, 60, 20)];
    username.text = FEString(@"USERNAME");
    [self.view addSubview:username];
    
    FELabel *name = [[FELabel alloc] initWithFrame:CGRectMake(90, 30, 200, 20)];
    name.text = user.username;
    [self.view addSubview:name];
    
    FELabel *email = [[FELabel alloc] initWithFrame:CGRectMake(20, 70, 60, 20)];
    email.text = FEString(@"EMAIL");
    [self.view addSubview:email];
    
    UITextField *emailText = [[UITextField alloc] initWithFrame:CGRectMake(90, 70, 200, 30)];
    emailText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:emailText];
    
    UIButton *mod = [UIButton buttonWithType:UIButtonTypeCustom];
    mod.frame = CGRectMake(80, 150, 160, 30);
    [mod setTitle:FEString(@"MODIFY") forState:UIControlStateNormal];
    [self.view addSubview:mod];
    
    UIButton *logout = [UIButton buttonWithType:UIButtonTypeCustom];
    logout.frame = CGRectMake(80, 180, 160, 30);
    [logout setTitle:FEString(@"LOGOUT") forState:UIControlStateNormal];
    [logout addTarget:self action:@selector(logout:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:logout];
    
}

-(void)rightbarpressed:(UIButton *)button{
    FEModifyPassWord *psw = [FEModifyPassWord new];
    [self.navigationController pushViewController:psw animated:YES];
}

-(void)logout:(UIButton *)button{
//    FELoginUser
    [FECoreData deleteCoreData:@[FELoginUser]];
    [FECoreData saveCoreData];
    
    [[AppDelegate sharedDelegate] loadSigin];
    
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

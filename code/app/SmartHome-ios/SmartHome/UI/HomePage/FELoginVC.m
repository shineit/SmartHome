//
//  FELoginVC.m
//  SmartHome
//
//  Created by Seven on 14-10-17.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FELoginVC.h"
#import "AppDelegate.h"

@interface FELoginVC ()

@property(nonatomic, strong) UITextField *username;
@property(nonatomic, strong) UITextField *password;

@end

@implementation FELoginVC

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
    self.view.backgroundColor = [UIColor orangeColor];
    //user name textfield
    UITextField *username = [[UITextField alloc] initWithFrame:CGRectMake(50, 200, 220, 30)];
    username.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:username];
    self.username = username;
    
    UITextField *psw = [[UITextField alloc] initWithFrame:CGRectMake(50, 250, 220, 30)];
    psw.secureTextEntry = YES;
    psw.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:psw];
    self.password = psw;
    
    UIButton *loginbtn = [UIButton buttonWithType:UIButtonTypeRoundedRect];
    loginbtn.frame = CGRectMake(100, 290, 120, 30);
    [loginbtn setTitle:FEString(@"LOGIN") forState:UIControlStateNormal];
    [loginbtn addTarget:self action:@selector(login:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:loginbtn];
    
}

-(void)login:(UIButton *)button{
    
    [[AppDelegate sharedDelegate] loadMain];
    
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

//
//  FELoginVC.m
//  SmartHome
//
//  Created by Seven on 14-10-17.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FELoginVC.h"
#import "AppDelegate.h"

@interface FELoginVC ()<UITextFieldDelegate>

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
    username.returnKeyType = UIReturnKeyNext;
    username.placeholder = FEString(@"INPUT_USER_NAME");
    username.borderStyle = UITextBorderStyleRoundedRect;
    username.delegate = self;
    [self.view addSubview:username];
    self.username = username;
    
    UITextField *psw = [[UITextField alloc] initWithFrame:CGRectMake(50, 250, 220, 30)];
    psw.returnKeyType = UIReturnKeyDone;
    psw.placeholder = FEString(@"INPUT_PASSWORD");
    psw.secureTextEntry = YES;
    psw.borderStyle = UITextBorderStyleRoundedRect;
    psw.delegate = self;
    [self.view addSubview:psw];
    self.password = psw;
    
    UIButton *loginbtn = [UIButton buttonWithType:UIButtonTypeRoundedRect];
    loginbtn.frame = CGRectMake(100, 290, 120, 30);
    [loginbtn setTitle:FEString(@"LOGIN") forState:UIControlStateNormal];
    [loginbtn addTarget:self action:@selector(login:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:loginbtn];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard)];
    [self.view addGestureRecognizer:tap];

}

-(void)login:(UIButton *)button{
    [self hideKeyboard];
    
    if (![self.username.text isEqualToString:@""] && ![self.password.text isEqualToString:@""]) {
        [[AppDelegate sharedDelegate] loadMain];
    }else{
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"SmartHome" message:FEString(@"PLS_INPUT_") delegate:nil cancelButtonTitle:FEString(@"OK") otherButtonTitles:nil];
        [alert show];
    }
    
    
}

#pragma mark - UITextFieldDelegate
-(void)textFieldDidEndEditing:(UITextField *)textField{
    
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField{
    if (textField == self.username) {
        [self.password becomeFirstResponder];
    }else if (textField == self.password){
        [self login:nil];
    }
    return YES;
}

-(void)hideKeyboard{
    [self.username resignFirstResponder];
    [self.password resignFirstResponder];
}

-(void)keyboardWillHide:(CGRect)newRect duration:(NSTimeInterval)duration{
    [self screenOffset:0];
}

-(void)keyboardWillShow:(CGRect)newRect duration:(NSTimeInterval)duration{
    [self screenOffset:[UIDevice is4Inch]?SYSTEM_VERSION_UP7?-0:-0:SYSTEM_VERSION_UP7?-80:-80];
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

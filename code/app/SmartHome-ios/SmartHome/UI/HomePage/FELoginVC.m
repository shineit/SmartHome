//
//  FELoginVC.m
//  SmartHome
//
//  Created by Seven on 14-10-17.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FELoginVC.h"
#import "AppDelegate.h"
#import "CDUser.h"
#import "FEWebServiceManager.h"

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
    self.view.backgroundColor = FEThemeColor;
    //logo
    CGFloat adjust = [UIDevice is4Inch]?40:0;
    
    UIImageView *logo = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, 145, 145)];
    logo.image = [UIImage imageNamed:@"sigin_logo"];
    logo.center = CGPointMake(self.view.bounds.size.width / 2, adjust + 120);
    [self.view addSubview:logo];
    
    UIImageView *textcontent = [[UIImageView alloc] initWithFrame:CGRectMake(20, logo.frame.origin.y + logo.bounds.size.height + 30, self.view.bounds.size.width - 40, 101)];
    textcontent.image = [UIImage imageNamed:@"sigin_input"];
    textcontent.userInteractionEnabled = YES;
    [self.view addSubview:textcontent];

    FELabel *namelabel = [[FELabel alloc] initWithFrame:CGRectMake(5, 10, 70, 30)];
    namelabel.text = FEString(@"USER_NAME:");
    [textcontent addSubview:namelabel];
    
    //user name textfield
    FETextField *username = [[FETextField alloc] initWithFrame:CGRectMake(namelabel.frame.origin.x + namelabel.bounds.size.width + 5, namelabel.frame.origin.y, textcontent.bounds.size.width - (namelabel.frame.origin.x + namelabel.bounds.size.width + 5) - 10, 30)];
    username.returnKeyType = UIReturnKeyNext;
    username.keyboardType = UIKeyboardTypeEmailAddress;
    username.borderStyle = UITextBorderStyleNone;
//    username.placeholder = FEString(@"INPUT_USER_NAME");
    username.delegate = self;
    [textcontent addSubview:username];
    self.username = username;
    
    
    FELabel *pswlabel = [[FELabel alloc] initWithFrame:CGRectMake(5, 60, 70, 30)];
    pswlabel.text = FEString(@"USER_PSW:");
    [textcontent addSubview:pswlabel];
    
    FETextField *psw = [[FETextField alloc] initWithFrame:CGRectMake(pswlabel.frame.origin.x + pswlabel.bounds.size.width + 5, pswlabel.frame.origin.y, textcontent.bounds.size.width - (pswlabel.frame.origin.x + pswlabel.bounds.size.width + 5) - 10, 30)];
    psw.returnKeyType = UIReturnKeyDone;
    psw.borderStyle = UITextBorderStyleNone;
//    psw.placeholder = FEString(@"INPUT_PASSWORD");
    psw.secureTextEntry = YES;
    psw.delegate = self;
    [textcontent addSubview:psw];
    self.password = psw;
    
    UIButton *loginbtn = [UIButton buttonWithType:UIButtonTypeCustom];
    loginbtn.frame = CGRectMake(50, textcontent.frame.origin.y + textcontent.bounds.size.height + 20, self.view.bounds.size.width - 100, 40);
    loginbtn.layer.cornerRadius = 5;
    loginbtn.layer.masksToBounds = YES;
    [loginbtn setTitle:FEString(@"LOGIN") forState:UIControlStateNormal];
    [loginbtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [loginbtn setBackgroundImage:[UIImage imageFromColor:FEButtonColor] forState:UIControlStateNormal];
    [loginbtn addTarget:self action:@selector(login:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:loginbtn];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard)];
    [self.view addGestureRecognizer:tap];

}

-(void)login:(UIButton *)button{
    [self hideKeyboard];
    if (![self.username.text isEqualToString:@""] && ![self.password.text isEqualToString:@""]) {
        [self displayHUD:FEString(@"LOADING")];
        __weak typeof(self) weakself = self;
//        NSJSONSerialization *json = [NSJSONSerialization ]
        
//        [NSString stringWithFormat:@"username=%@,password=%@",self.username.text,[self.password.text MD5]]
        NSData *data = [NSJSONSerialization dataWithJSONObject:@{@"userName":self.username.text,@"passWord":[self.password.text MD5]} options:NSJSONWritingPrettyPrinted error:NULL];
        [[FEWebServiceManager sharedInstance] siginWithParam:data response:^(NSError *error, FEDataUser *user){
            NSLog(@"call back");
            [weakself hideHUD:YES];
            if (error) {
                return;
            }
            dispatch_async(dispatch_get_main_queue(), ^(void){
                CDUser *user = [FECoreData touchUserByIdentifier:@"identifier"];
                user.username = weakself.username.text;
                user.password = [weakself.password.text MD5];
                [FECoreData saveCoreData];
                [[AppDelegate sharedDelegate] loadMain];
            });
            
        }];
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
    [self screenOffset:[UIDevice is4Inch]?SYSTEM_VERSION_UP7?-100:-100:SYSTEM_VERSION_UP7?-160:-160];
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

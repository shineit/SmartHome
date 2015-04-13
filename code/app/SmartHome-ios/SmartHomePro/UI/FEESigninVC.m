//
//  FEESigninVC.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEESigninVC.h"
#import "FEButton.h"
#import "UIColor+Theme.h"
#import <ZBUtilities/UIImage+LogN.h>
#import "AppDelegate.h"
#import "FECommonDefine.h"
#import "FESiginRequest.h"
#import "NSString+MD5.h"
#import "FEWebServiceManager.h"
#import "FESiginResponse.h"
#import "FEMemoryCache.h"
#import <ZBUtilities/UIDevice+ZBUtilites.h>

@interface FEESigninVC ()
@property (strong, nonatomic) IBOutlet FEButton *loginBtn;
@property (strong, nonatomic) IBOutlet UITextField *usernameTextFeild;
@property (strong, nonatomic) IBOutlet UITextField *pwdTextFeild;

@end

@implementation FEESigninVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.loginBtn setBackgroundImage:[UIImage imageFromColor:[UIColor ThemeColor]] forState:UIControlStateNormal];
    UITapGestureRecognizer *ges = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard)];
    [self.view addGestureRecognizer:ges];
}

- (IBAction)signin:(id)sender {
//    [[AppDelegate sharedDelegate] loadMain];
    [self login:nil];
}


-(void)login:(UIButton *)button{
    [self hideKeyboard];
    if (![self.usernameTextFeild.text isEqualToString:@""] && ![self.pwdTextFeild.text isEqualToString:@""]) {
        [self displayHUD:kString(@"LOADING")];
        __weak typeof(self) weakself = self;
        
        //        int returnCode = [[bpushres valueForKey:BPushRequestErrorCodeKey] intValue];
        //        NSString *requestid = [bpushres valueForKey:BPushRequestRequestIdKey];
        
        FESiginRequest *sdata = [[FESiginRequest alloc] initWtihUserName:self.usernameTextFeild.text password:[self.pwdTextFeild.text MD5] clientType:@"1" clientVersion:@"1.0" devToken:kUserDefaultsObjectForKey(kDeviceToken) push_id:nil push_userid:nil push_channelID:nil];
        
        [[FEWebServiceManager sharedInstance] siginWithParam:sdata response:^(NSError *error, FESiginResponse *user){
            NSLog(@"call back");
            [weakself hideHUD:YES];
            if (!error && user.result.errorCode.integerValue == 0) {
                FEUser *lUser = user.user;
                [FEMemoryCache sharedInstance].user = lUser;
                if (lUser) {
                    kUserDefaultsSetObjectForKey(lUser.dictionary, kLoginUser);
                    dispatch_async(dispatch_get_main_queue(), ^(void){
                        [[AppDelegate sharedDelegate] loadMain];
                    });
                }
                
            }
            
        }];
    }else{
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"SmartHome" message:kString(@"PLS_INPUT_") delegate:nil cancelButtonTitle:kString(@"OK") otherButtonTitles:nil];
        [alert show];
    }
    
}

#pragma mark - UITextFeildDelegate
- (BOOL)textFieldShouldReturn:(UITextField *)textField{
    if (textField == self.usernameTextFeild) {
        [self.pwdTextFeild becomeFirstResponder];
    }else if (textField == self.pwdTextFeild){
        [self login:nil];
    }
    return YES;
}

-(void)keyboardWillHide:(CGRect)newRect duration:(NSTimeInterval)duration{
    [self screenOffset:0];
}

-(void)keyboardWillShow:(CGRect)newRect duration:(NSTimeInterval)duration{
    [self screenOffset:[UIDevice is4Inch]?-100:-160];
}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}




-(void)hideKeyboard{
    [self.usernameTextFeild resignFirstResponder];
    [self.pwdTextFeild resignFirstResponder];
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end

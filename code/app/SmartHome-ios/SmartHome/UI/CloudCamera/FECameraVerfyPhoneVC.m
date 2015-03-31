//
//  FECameraVerfyPhomeVC.m
//  SmartHome
//
//  Created by Seven on 15-1-29.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECameraVerfyPhoneVC.h"
#import "YSHTTPClient.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "YSCamera.h"
#import "FEWebServiceManager.h"
#import "FECoreDataHandler.h"
#import "CDUser.h"
#import "AppDelegate.h"
#import "NSString+YSParam.h"

@interface FECameraVerfyPhoneVC ()
@property (strong, nonatomic) IBOutlet UITextField *phoneNumberTextField;
@property (strong, nonatomic) IBOutlet UITextField *codeTextfield;

@end

@implementation FECameraVerfyPhoneVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    self.phoneNumberTextField.text = self.phoneNumber;

}

-(void)msg{
    
    NSDictionary *map = @{@"type":@(1),@"userId":FELoginUser.userid,@"phone":self.phoneNumberTextField.text};

    [[YSHTTPClient sharedInstance] requestGetSMSVerificationCodeWithSign:[NSString ysParam:map method:@"msg/get" appSecret:YSAppSecret appKey:YSAppKey] complication:^(id responseObject, NSError *error) {
        NSLog(@"%@",responseObject);
    }];
}
- (IBAction)sign:(id)sender {
    if (self.codeTextfield.text.length) {
        [[YSHTTPClient sharedInstance] requestCheckSMSVerificationCodeWithType:1 userId:FELoginUser.userid.stringValue phoneNumber:self.phoneNumberTextField.text verificationCode:self.codeTextfield.text complication:^(id responseObject, NSError *error) {
            if (!error) {
                NSLog(@"%@",responseObject);
            }
        }];
    }
    
    
}

- (IBAction)verfy:(id)sender {
    [self msg];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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

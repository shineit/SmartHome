//
//  FECameraShouldVerfyVC.m
//  SmartHome
//
//  Created by Seven on 15-1-30.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECameraShouldVerfyVC.h"
//#import "CustomIOS7AlertView.h"
#import "FEMarkAddPopView.h"
#import "YSCamera.h"
#import "FEWebServiceManager.h"
#import "FECoreDataHandler.h"
#import "CDUser.h"
#import "AppDelegate.h"
#import "FECameraVerfyPhoneVC.h"
#import "FECloudCameraVC.h"


@interface FECameraShouldVerfyVC ()<CustomIOS7AlertViewDelegate>

@end

@implementation FECameraShouldVerfyVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"授权登陆");
        if (SYSTEM_VERSION_UP7) {
            UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:FEString(@"CLOUD_CAMERA") image:[UIImage imageNamed:@"tabbar_camera"] selectedImage:nil];
            self.tabBarItem = tabitem;
        }else{
            [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"tabbar_camera_select"] withFinishedUnselectedImage:[UIImage imageNamed:@"tabbar_camera"]];
        }
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
}
- (IBAction)verfyPhone:(id)sender {
//    CustomIOS7AlertView *alert = [[CustomIOS7AlertView alloc] init];
//    [alert show];
    FEMarkAddPopView *pop = [[FEMarkAddPopView alloc] initWithTitle:@"请输入手机号" Titles:@[@"确定",@"取消"]];
    pop.delegate = self;
    [pop show];
}

-(void)customIOS7dialogButtonTouchUpInside:(id)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (buttonIndex == 0) {
        [self signWithPhone:((FEMarkAddPopView *)alertView).textField.text];
    }else if(buttonIndex == 1){
        NSLog(@"1");
    }
    [alertView close];
}

-(void)signWithPhone:(NSString *)phone{
    //4548
    [self displayHUD:@"连接中..."];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] getCatokenWithParam:[[FEGetCaTokenRequest alloc] initWithUserID:FELoginUser.userid phone:phone] response:^(NSError *error, FEGetCaTokenResponse *response) {
        
        if (!error){
            if(response.result.errorCode.integerValue == 10004){
                FECameraVerfyPhoneVC *vc = [[FECameraVerfyPhoneVC alloc] initWithNibName:@"FECameraVerfyPhoneVC" bundle:nil];
                vc.phoneNumber = phone;
                [weakself.navigationController pushViewController:vc animated:YES];
            }else if(response.result.errorCode.integerValue == 0){
                FECloudCameraVC *cvc = [[FECloudCameraVC alloc] init];
                cvc.hidesBottomBarWhenPushed = YES;
                cvc.accessToken = response.caToken.accessToken;
                [weakself.navigationController pushViewController:cvc animated:YES];
            }
        }
        [weakself hideHUD:YES];
    }];
    
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

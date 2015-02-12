//
//  FECameraWifiConfigVC.m
//  SmartHome
//
//  Created by Seven on 15-2-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECameraWifiConfigVC.h"
#import <SystemConfiguration/CaptiveNetwork.h>
#import "WifiAddDeviceViewController.h"

@interface FECameraWifiConfigVC ()
@property (strong, nonatomic) IBOutlet UITextField *SSIDTextField;
@property (strong, nonatomic) IBOutlet UITextField *passwordTexfield;
@property (strong, nonatomic) NSString *ssid;

@end

@implementation FECameraWifiConfigVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
     NSDictionary *ifs = [self currentNetworkInfo];
    self.ssid = [ifs objectForKey:@"SSID"];
    self.SSIDTextField.text = self.ssid;
    if (self.ssid.length == 0) {
        [self showAlert];
    }
}

-(void)showAlert{
    UIAlertView * alertView = [[UIAlertView alloc] initWithTitle:@"需要将手机连接到Wi-Fi"
                                                         message:@"请到“设置”-“Wi-Fi”功能中开启Wi-Fi并连接到网络"
                                                        delegate:self
                                               cancelButtonTitle:NSLocalizedString(@"OK", nil)
                                               otherButtonTitles: nil];
    
    [alertView show];
}


- (id)currentNetworkInfo
{
    NSArray *ifs = (__bridge_transfer NSArray *)CNCopySupportedInterfaces();
    id info = nil;
    for (NSString *ifnam in ifs)
    {
        info = (__bridge id)CNCopyCurrentNetworkInfo((__bridge CFStringRef)ifnam);
        if (info && [(NSDictionary *)info count])
        {
            break;
        }
    }
    return info;
}

- (IBAction)next:(id)sender {
    if (self.ssid.length == 0) {
        [self showAlert];
    }else{
        WifiAddDeviceViewController *vc = [[WifiAddDeviceViewController alloc] init];
        vc.ccode = self.ccode;
        vc.strSsid = self.SSIDTextField.text;
        vc.strKey = self.passwordTexfield.text;
        vc.accessToken = self.accessToken;
        [self.navigationController pushViewController:vc animated:YES];
    }
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

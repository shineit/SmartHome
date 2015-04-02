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
}

- (IBAction)signin:(id)sender {
//    [[AppDelegate sharedDelegate] loadMain];
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

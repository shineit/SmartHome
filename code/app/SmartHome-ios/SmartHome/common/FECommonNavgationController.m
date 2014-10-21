//
//  FECommonNavgationController.m
//  SmartHome
//
//  Created by Seven on 14-10-17.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECommonNavgationController.h"

@interface FECommonNavgationController ()

@end

@implementation FECommonNavgationController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.navigationBar.titleTextAttributes = @{UITextAttributeTextColor: [UIColor whiteColor]};
        if (SYSTEM_VERSION_UP7) {
            self.navigationBar.translucent = NO;
            self.navigationBar.barTintColor = FEThemeColor;//[UIColor orangeColor];
        }else{
            self.navigationBar.tintColor = FEThemeColor;
        }
    }
    return self;
}

-(instancetype)init{
    self = [super init];
    if (self) {
        self.navigationBar.titleTextAttributes = @{UITextAttributeTextColor: [UIColor whiteColor]};
        if (SYSTEM_VERSION_UP7) {
            self.navigationBar.translucent = NO;
            self.navigationBar.barTintColor = FEThemeColor;
        }else{
            self.navigationBar.tintColor = FEThemeColor;
        }
    }
    return self;
}

- (instancetype)initWithRootViewController:(UIViewController *)rootViewController{
    self = [super initWithRootViewController:rootViewController];
    if (self) {
        self.navigationBar.titleTextAttributes = @{UITextAttributeTextColor: [UIColor whiteColor]};
        if (SYSTEM_VERSION_UP7) {
            self.navigationBar.translucent = NO;
            self.navigationBar.barTintColor = FEThemeColor;
        }else{
            self.navigationBar.tintColor = FEThemeColor;
        }
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
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

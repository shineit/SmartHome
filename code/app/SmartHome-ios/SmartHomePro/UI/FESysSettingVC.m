//
//  FESysSettingVC.m
//  SmartHome
//
//  Created by Seven on 15-4-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FESysSettingVC.h"
#import "FECommonDefine.h"


@interface FESysSettingVC ()
@property (strong, nonatomic) IBOutlet UIImageView *showImage;
@property (strong, nonatomic) IBOutlet UISwitch *switcher;

@end

@implementation FESysSettingVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    NSNumber *mute = kUserDefaultsObjectForKey(kMute);
    if (mute) {
        self.switcher.on = !mute.boolValue;
    }else{
        self.switcher.on = YES;
    }
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)change:(id)sender {
    if (self.switcher.on) {
//        kUserDefaultsObjectForKey(kMute);
        kUserDefaultsSetObjectForKey(@(NO), kMute);
        
    }else{
        kUserDefaultsSetObjectForKey(@(YES), kMute);
    }
    kUserDefaultsSync;
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

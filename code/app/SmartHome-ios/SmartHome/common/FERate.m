//
//  FERate.m
//  SmartHome
//
//  Created by Seven on 14-10-28.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERate.h"
#import "FECommonDefine.h"
#import <UIKit/UIKit.h>

@implementation FERate

DEFINE_SINGLETON_FOR_CLASS(FERate);

//+(void)load{
//    FERate *rate = [FERate sharedFERate];
//    NSInteger runtimes = [kUserDefaultsObjectForKey(kRunTimes) integerValue];
//    runtimes++;
//    kUserDefaultsSetObjectForKey(@(runtimes), kRunTimes);
//    kUserDefaultsSync;
//    
//    if (![kUserDefaultsObjectForKey(kRateNever) boolValue] && runtimes >= kRateTimes) {
//        [rate performSelector:@selector(rate:) withObject:nil afterDelay:5];
//    }
//}

-(void)rate:(id)sender{
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:kString(@"SMART_HOME") message:kString(@"RATE_RATE") delegate:self cancelButtonTitle:kString(@"RATE_LARER") otherButtonTitles:kString(@"RATE_GOTORATE"),kString(@"RATE_NEVER"), nil];
    [alert show];
}

-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    switch (buttonIndex) {
        case 0:
            break;
            
        case 1:
            [self rate];
            kUserDefaultsSetObjectForKey(@(YES), kRateNever);
            kUserDefaultsSync;
            
            break;
        case 2:
            kUserDefaultsSetObjectForKey(@(YES), kRateNever);
            kUserDefaultsSync;
            break;
        default:
            break;
    }
}

//去评分
-(void)rate{
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:kItunesUrlString(kAppItunesID)]];
}

@end

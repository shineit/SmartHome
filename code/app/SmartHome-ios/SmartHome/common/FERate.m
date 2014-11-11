//
//  FERate.m
//  SmartHome
//
//  Created by Seven on 14-10-28.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERate.h"

@implementation FERate

DEFINE_SINGLETON_FOR_CLASS(FERate);

+(void)load{
    FERate *rate = [FERate sharedFERate];
    NSInteger runtimes = [FEUserDefaultsObjectForKey(FERunTimes) integerValue];
    runtimes++;
    FEUserDefaultsSetObjectForKey(@(runtimes), FERunTimes);
    FEUserDefaultsSync;
    
    if (![FEUserDefaultsObjectForKey(FERateNever) boolValue] && runtimes >= FERateTimes) {
        [rate performSelector:@selector(rate:) withObject:nil afterDelay:5];
    }
}

-(void)rate:(id)sender{
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:FEString(@"SMART_HOME") message:FEString(@"RATE_RATE") delegate:self cancelButtonTitle:FEString(@"RATE_LARER") otherButtonTitles:FEString(@"RATE_GOTORATE"),FEString(@"RATE_NEVER"), nil];
    [alert show];
}

-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    switch (buttonIndex) {
        case 0:
            break;
            
        case 1:
            [self rate];
            FEUserDefaultsSetObjectForKey(@(YES), FERateNever);
            FEUserDefaultsSync;
            
            break;
        case 2:
            FEUserDefaultsSetObjectForKey(@(YES), FERateNever);
            FEUserDefaultsSync;
            break;
        default:
            break;
    }
}

//去评分
-(void)rate{
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:FEItunesUrlString(FEAppItunesID)]];
}

@end

//
//  FECommonDefine.h
//  SmartHome
//
//  Created by Seven on 15-3-31.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#ifndef SmartHome_FECommonDefine_h
#define SmartHome_FECommonDefine_h

//Singleton define
#define DEFINE_SINGLETON_FOR_HEADER(className) \
\
+ (className *)shared##className;
//Singleton method
#define DEFINE_SINGLETON_FOR_CLASS(className) \
\
+ (className *)shared##className { \
static className *shared##className = nil; \
static dispatch_once_t onceToken; \
dispatch_once(&onceToken, ^{ \
shared##className = [[self alloc] init]; \
}); \
return shared##className; \
}



#define kColor(_R,_G,_B,_A)                    [UIColor colorWithRed:_R / 255.0f green:_G / 255.0f blue: _B / 255.0f alpha:_A]

#define kString(_S)                            NSLocalizedString(_S, @"")


// App infomation
#define kDidLaunchedFirstTime(_V)              [NSString stringWithFormat:@"DidLaunchedFirstTime V-%@",_V]
#define kAppVersion                            [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleVersion"]
#define	kAppIdentifier                         [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleIdentifier"]
#define kAppBuildVersion                       [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleShortVersionString"]
#define kRateTimes                            [[[NSBundle mainBundle] objectForInfoDictionaryKey:@"RateTimes"] integerValue]
#define kAppItunesID                           [[NSBundle mainBundle] objectForInfoDictionaryKey:@"AppItunesIdentifier"]

//NSUserDefaults

#define kUserDefaults                          [NSUserDefaults standardUserDefaults]
#define kUserDefaultsObjectForKey(_KEY)        [kUserDefaults objectForKey:_KEY]
#define kUserDefaultsSetObjectForKey(_O,_KEY)  [kUserDefaults setObject:_O forKey:_KEY]
#define kUserDefaultsRemoveForKey(_KEY)        [kUserDefaults removeObjectForKey:_KEY]
#define kUserDefaultsSync                      [kUserDefaults synchronize]

//device token
#define kDeviceToken                           @"deviceToken"

//Run times
#define kRunTimes                              @"runTimes"

//never grade
#define kRateNever                            @"rateNever"

#define kLoginUser                              @"user_key"

#define kCustomerUser                           @"customer"

#define kMute                                   @"mute"

#define kAlarmNotification                      @"alarmNotification"

//itunes url
#define kItunesUrlString(_ID)                  [NSString stringWithFormat:@"itms-apps://ax.itunes.apple.com/WebObjects/MZStore.woa/wa/viewContentsUserReviews?type=Purple+Software&id=%@",_ID]

#endif

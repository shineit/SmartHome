//
//  FESmartHomeConfig.h
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#ifndef SmartHome_FESmartHomeConfig_h
#define SmartHome_FESmartHomeConfig_h

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


//LocalString
#define FEString(_S)                            NSLocalizedString(_S, @"")

// App infomation
#define FEDidLaunchedFirstTime(_V)              [NSString stringWithFormat:@"DidLaunchedFirstTime V-%@",_V]
#define	FEAppVersion                            [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleVersion"]
#define	FEAppIdentifier                         [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleIdentifier"]
#define FEAppBuildVersion                       [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleShortVersionString"]
#define FERateTimes                            [[[NSBundle mainBundle] objectForInfoDictionaryKey:@"RateTimes"] integerValue]
#define FEAppItunesID                           [[NSBundle mainBundle] objectForInfoDictionaryKey:@"AppItunesIdentifier"]

//NSUserDefaults

#define FEUserDefaults                          [NSUserDefaults standardUserDefaults]
#define FEUserDefaultsObjectForKey(_KEY)        [FEUserDefaults objectForKey:_KEY]
#define FEUserDefaultsSetObjectForKey(_O,_KEY)  [FEUserDefaults setObject:_O forKey:_KEY]
#define FEUserDefaultsRemoveForKey(_KEY)        [FEUserDefaults removeObjectForKey:_KEY]
#define FEUserDefaultsSync                      [FEUserDefaults synchronize]

//device token
#define FEDeviceToken                           @"deviceToken"

//Run times
#define FERunTimes                              @"runTimes"

//never grade
#define FERateNever                            @"rateNever"

#define FEBPushResKey                           @"bpushkey"

//itunes url
#define FEItunesUrlString(_ID)                  [NSString stringWithFormat:@"itms-apps://ax.itunes.apple.com/WebObjects/MZStore.woa/wa/viewContentsUserReviews?type=Purple+Software&id=%@",_ID]

//uicolor
#define FEColor(_R,_G,_B,_A)                    [UIColor colorWithRed:_R / 255.0f green:_G / 255.0f blue: _B / 255.0f alpha:_A]

#define FEThemeColor                            FEColor(252, 156, 56, 1)
#define FEButtonColor                           FEColor(250, 177, 60, 1)
#define FEGrayButtonColor                       FEColor(202,202,202,1)
#define FEViewBackGroundColor                   FEColor(249, 249, 249, 1)

#define FECoreData                              ([AppDelegate sharedDelegate].coreDataHandler)

#define FELoginUser                             [FECoreData fetchUser]

#define FEMarkDidChangeNotification             @"markchange"

#define YSAppKey        @"9a39449992d048439b4cef7d62a3c997"
#define YSAppSecret     @"2e49fa81764d370c2693a5f1ed0d8048"


#endif

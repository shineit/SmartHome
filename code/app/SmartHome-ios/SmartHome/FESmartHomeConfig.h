//
//  FESmartHomeConfig.h
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#ifndef SmartHome_FESmartHomeConfig_h
#define SmartHome_FESmartHomeConfig_h


//uicolor
#define FEColor(_R,_G,_B,_A)                    [UIColor colorWithRed:_R / 255.0f green:_G / 255.0f blue: _B / 255.0f alpha:_A]

//#define FEThemeColor                            FEColor(252, 156, 56, 1)
#define FEButtonColor                           FEColor(250, 177, 60, 1)
#define FEGrayButtonColor                       FEColor(202,202,202,1)
#define FEViewBackGroundColor                   FEColor(249, 249, 249, 1)

#define FECoreData                              ([AppDelegate sharedDelegate].coreDataHandler)

#define FELoginUser                             [FECoreData fetchUser]

#define FEMarkDidChangeNotification             @"markchange"

#define YSAppKey        @"9a39449992d048439b4cef7d62a3c997"
#define YSAppSecret     @"2e49fa81764d370c2693a5f1ed0d8048"


#endif

//
//  Define.h
//  SmartHome
//
//  Created by Seven on 15-3-31.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#ifndef SmartHome_Define_h
#define SmartHome_Define_h


#define kServerIP               @"115.231.168.14"
#define kServerPort             @"8080"
#define kBasePath               @"/SmartHome"
#define kImagePath              @"/upload/"

#define kImageURL(_A)           [NSString stringWithFormat:@"http://%@:%@%@%@%@",kServerIP,kServerPort,kBasePath,kImagePath,_A]

#define __SERVICE_BASE_URL    [NSString stringWithFormat:@"http://%@:%@%@/rest",kServerIP,kServerPort,kBasePath]


#define kString(_S)                            NSLocalizedString(_S, @"")

#endif

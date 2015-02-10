//
//  YSCamera.h
//  SmartHome
//
//  Created by Seven on 15-1-13.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <SSCommon-Utilities/SSObject.h>

@interface YSCamera : SSObject

@property (nonatomic, strong) NSString *cameraId;
@property (nonatomic, strong) NSString *cameraName;
@property (nonatomic, strong) NSString *cameraNo;
@property (nonatomic, strong) NSString *deviceId;
@property (nonatomic, strong) NSString *display;
@property (nonatomic, strong) NSString *isEncrypt;
@property (nonatomic, strong) NSString *isShared;
@property (nonatomic, strong) NSString *picUrl;
@property (nonatomic, strong) NSString *status;

@end

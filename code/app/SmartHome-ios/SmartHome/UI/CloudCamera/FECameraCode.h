//
//  FECameraCode.h
//  SmartHome
//
//  Created by Seven on 15-2-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FECameraCode : NSObject

@property (nonatomic, strong) NSString * m_strSN;            // 识别好的序列号
@property (nonatomic, strong) NSString * strVerifyCode;     // 识别的验证码
@property (nonatomic, strong) NSString * strModel;          // 识别的设备型号

@property (nonatomic, strong) NSString *m_strAESVersion;     //识别设备AES加密
@property (nonatomic, strong) NSString *m_strDetectorSubType;   //识别探测器类型

//标示A1设备关联扫描,默认为NO
//@property (nonatomic, strong) BOOL m_isA1AssociateScanning;//场景:从A1详情界面进入扫描界面
//@property (nonatomic, strong) NSArray *m_detectorlist; ////存储A1已关联的探测器,应用场景同m_isA1AssociateScanning
//@property (nonatomic, strong) CDeviceInfo *m_deviceA1; //存储A1设备数据,应用场景同m_isA1AssociateScanning

-(id)initWithString:(NSString *)code;

@end

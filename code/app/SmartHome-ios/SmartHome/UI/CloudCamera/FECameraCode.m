//
//  FECameraCode.m
//  SmartHome
//
//  Created by Seven on 15-2-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECameraCode.h"

@implementation FECameraCode

-(id)initWithString:(NSString *)code{
    self = [super init];
    if (self) {
        [self analyzeCodeString:code];
    }
    return self;
}

-(void)analyzeCodeString:(NSString *)strQRcode{
    self.m_strDetectorSubType = nil;
    NSString * strSN = nil;
    NSArray * arrString = [strQRcode componentsSeparatedByCharactersInSet:[NSCharacterSet newlineCharacterSet]];
    
    //空字符串过滤
    NSMutableArray *rcodeMutAry = [NSMutableArray arrayWithArray:arrString];
    NSMutableArray *copyRcodeMutAry = [NSMutableArray arrayWithArray:arrString];
    for (NSString *strMge in copyRcodeMutAry)
    {
        if ([strMge length] <= 1)
        {
            [rcodeMutAry removeObject:strMge];
        }
    }
    
    int nStringCount = [rcodeMutAry count];
    if (nStringCount == 1)
    {
        strSN = [NSString stringWithFormat:@"%@", [rcodeMutAry objectAtIndex:0]];
    }
    else if (nStringCount > 1)
    {
        strSN = [NSString stringWithFormat:@"%@", [rcodeMutAry objectAtIndex:1]];
    }
    else
    {
        self.strVerifyCode = nil;
        self.strModel = nil;
        self.m_strDetectorSubType = nil;
//        return nil;
        strSN = nil;
    }
    
    self.strVerifyCode = nil;
    if (nStringCount > 2)
    {
        self.strVerifyCode = [rcodeMutAry objectAtIndex:2];
        if ([self.strVerifyCode length] < 6)
        {
            self.strVerifyCode = nil;
        }
    }
    
    self.strModel = nil;
    if (nStringCount > 3)
    {
        self.strModel = [rcodeMutAry objectAtIndex:3];
    }
    
    self.m_strDetectorSubType = nil;
    if (nStringCount > 4)
    {
        NSString *tmpstr = [rcodeMutAry objectAtIndex:4];
        if (4 == [tmpstr length]) //探测器子型号为四个字节(T001/K002)
        {
            self.m_strDetectorSubType = tmpstr;
        }
        
    }
    
    self.m_strSN = strSN;
//    return strSN;
}

@end

//
//  FEAdvertisement.h
//  SmartHome
//
//  Created by Seven on 15-4-7.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEAdvertisement : SSObject
//private int adID;
//private String adName;
//private String adInfo;
//private String adURL;
//private String adImg;

@property (nonatomic, strong) NSNumber *adID;
@property (nonatomic, strong) NSString *adName;
@property (nonatomic, strong) NSString *adInfo;
@property (nonatomic, strong) NSString *adURL;
@property (nonatomic, strong) NSString *adImg;

@end

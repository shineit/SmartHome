//
//  FEADResponse.h
//  SmartHome
//
//  Created by Seven on 15-4-7.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEAdvertisement.h"

@interface FEADResponse : FEBaseResponse

@property (nonatomic, strong, readonly) NSArray *adList;

@end

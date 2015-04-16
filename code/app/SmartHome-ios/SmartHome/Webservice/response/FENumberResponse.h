//
//  FENumberResponse.h
//  SmartHome
//
//  Created by Seven on 15-4-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEBageNumber.h"

@interface FENumberResponse : FEBaseResponse
@property (nonatomic, strong, readonly) NSArray *numList;

@end

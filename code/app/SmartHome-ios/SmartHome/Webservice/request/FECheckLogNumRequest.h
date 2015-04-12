//
//  FECheckLogNumRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FECheckLogNumRequest : FERequestBaseData
@property (nonatomic, strong) NSNumber *userID;

-(id)initWithUid:(NSNumber *)uid;
@end

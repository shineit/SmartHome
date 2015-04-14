//
//  FECustomerRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FECustomerRequest : FERequestBaseData
//private int userID;
@property (nonatomic, strong) NSNumber *userID;
-(id)initWithUid:(NSNumber *)uid;
@end

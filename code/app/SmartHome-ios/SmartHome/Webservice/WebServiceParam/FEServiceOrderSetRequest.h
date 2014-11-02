//
//  FEServiceOrderSetRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
#import "FEOrder.h"


@interface FEServiceOrderSetRequest : FERequestBaseData

@property (nonatomic, strong, readonly) NSString *command;
@property (nonatomic, strong, readonly) NSDictionary *serviceOrder;

-(id)initWithCmd:(NSString *)cmd serviceOrder:(FEOrder *)order;

@end

//
//  FEQueryPlanByIdRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FEQueryPlanByIdRequest : FERequestBaseData
@property (nonatomic, strong) NSNumber *planID;
-(id)initWithPid:(NSNumber *)pid;
@end

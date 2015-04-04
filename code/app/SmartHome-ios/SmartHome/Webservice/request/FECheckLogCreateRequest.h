//
//  FECheckLogCreateRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"


@interface FECheckLogCreateRequest : FERequestBaseData

@property (nonatomic, strong, readonly) NSArray *checkLogList;

-(id)initWithCheckLogList:(NSArray *)clog;

@end

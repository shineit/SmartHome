//
//  FECheckListRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FECheckListRequest : FERequestBaseData
@property (nonatomic, strong, readonly) NSNumber *companyID;
-(id)initWithCid:(NSNumber *)cid;
@end

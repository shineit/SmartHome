//
//  FEGetCompanyByIdRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FEGetCompanyByIdRequest : FERequestBaseData
@property (nonatomic, strong) NSNumber *companyID;

-(id)initWithCid:(NSNumber *)Cid;

@end

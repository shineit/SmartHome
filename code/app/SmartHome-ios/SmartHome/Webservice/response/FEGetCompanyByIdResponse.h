//
//  FEGetCompanyByIdResponse.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FECompany.h"

@interface FEGetCompanyByIdResponse : FEBaseResponse

@property (nonatomic, strong, readonly) FECompany *company;

@end

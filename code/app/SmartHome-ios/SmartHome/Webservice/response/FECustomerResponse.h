//
//  FECustomerResponse.h
//  SmartHome
//
//  Created by Seven on 15-4-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FECustomer.h"

@interface FECustomerResponse : FEBaseResponse

@property (nonatomic, strong) FECustomer *customer;

@end

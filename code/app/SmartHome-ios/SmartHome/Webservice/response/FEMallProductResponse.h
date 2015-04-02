//
//  FEMallProductResponse.h
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEProduct.h"

@interface FEMallProductResponse : FEBaseResponse
@property (nonatomic, strong) NSArray *productList;

@end

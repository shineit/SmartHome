//
//  FENewsRequest.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
#import "FEPage.h"
#import "FEAttribute.h"

@interface FENewsRequest : FERequestBaseData

@property (nonatomic, strong, readonly) FEPage *page;
@property (nonatomic, strong, readonly) NSArray *filterList; //FEAttribute


-(id)initWithPage:(FEPage *)page filter:(NSArray *)filer;

@end

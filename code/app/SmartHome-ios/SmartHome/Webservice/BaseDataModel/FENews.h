//
//  FENews.h
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FENews : SSObject

@property (nonatomic, strong, readonly) NSString *author;
@property (nonatomic, strong, readonly) NSString *content;
@property (nonatomic, strong, readonly) NSNumber *date;
@property (nonatomic, strong, readonly) NSNumber *newsID;
@property (nonatomic, strong, readonly) NSString *title;

@end

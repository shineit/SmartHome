//
//  NSString+Size.h
//  SmartHome
//
//  Created by Seven on 15-4-13.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface NSString (Size)

- (CGSize)boundingRectWithSize:(CGSize)size withTextFont:(UIFont *)font;

@end

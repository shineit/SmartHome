//
//  NSString+Size.m
//  SmartHome
//
//  Created by Seven on 15-4-13.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "NSString+Size.h"

@implementation NSString (Size)

-(CGSize)boundingRectWithSize:(CGSize)size
                 withTextFont:(UIFont *)font
{
    NSMutableAttributedString *attributedText = [self attributedStringFromStingWithFont:font];
    CGSize textSize = [attributedText boundingRectWithSize:size
                                                   options:NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingUsesFontLeading
                                                   context:nil].size;
    return textSize;
}

-(NSMutableAttributedString *)attributedStringFromStingWithFont:(UIFont *)font
{
    NSMutableAttributedString *attributedStr = [[NSMutableAttributedString alloc] initWithString:self attributes:@{NSFontAttributeName:font}];
    return attributedStr;
}

@end

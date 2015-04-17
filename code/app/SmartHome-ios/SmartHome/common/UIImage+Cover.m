//
//  UIImage+Cover.m
//  SmartHome
//
//  Created by Seven on 15-4-13.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "UIImage+Cover.h"

@implementation UIImage (Cover)

-(UIImage *)coverPoint:(CGPoint)point{
    
    return [self addMsakImage:nil msakRect:CGRectMake(point.x - 10, point.y - 10, 20, 20)];
    
}

- (UIImage *)addMsakImage:(UIImage *)maskImage msakRect:(CGRect)rect
{
    UIGraphicsBeginImageContext(self.size);
    [self drawInRect:CGRectMake(0, 0, self.size.width, self.size.height)];
    
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextSetRGBFillColor(context, 1.0f, 0, 0, 1);
//    CGContextSetStrokeColorWithColor(context, [UIColor redColor].CGColor);
    CGContextFillEllipseInRect(context, rect);
    //四个参数为水印图片的位置
    [maskImage drawInRect:rect];
    UIImage *resultingImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    if (resultingImage.size.width > resultingImage.size.height) {
        return [UIImage imageWithCGImage:resultingImage.CGImage scale:1.0 orientation:UIImageOrientationRight];
    }
    return resultingImage;
//    return [UIImage imageWithCGImage:resultingImage.CGImage scale:1.0 orientation:UIImageOrientationRight];
    
//    return resultingImage;
}

@end

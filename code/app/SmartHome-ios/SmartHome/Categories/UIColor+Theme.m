//
//  UIColor+Theme.m
//  SmartHome
//
//  Created by Seven on 14-10-27.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "UIColor+Theme.h"
#import "UIColor+Hex.h"

@implementation UIColor (Theme)

//+(UIColor *)themeColor{
//    return FEColor(<#_R#>, <#_G#>, <#_B#>, <#_A#>)
//}252, 156, 56, 1

+(UIColor *)ThemeColor{
    return [UIColor colorWithRed:252.0f / 255.0f green:156.0f / 255.0f blue:56.0f / 255.0f alpha:1.0f];
}

+(UIColor *)ThemeViewBackColor{
    
    return [UIColor colorWithHex:0xE7E6E3];//[UIColor colorWithRed:249.0f / 255.0f green:249.0f / 255.0f blue:249.0f / 255.0f alpha:1.0f];
}

@end

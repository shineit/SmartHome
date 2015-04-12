//
//  FEProductDetailVC.m
//  SmartHome
//
//  Created by Seven on 15-4-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEProductDetailVC.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "Define.h"
#import "FEProduct.h"

@interface FEProductDetailVC ()
@property (strong, nonatomic) IBOutlet UIImageView *productImageView;
@property (strong, nonatomic) IBOutlet UILabel *productDescription;

@end

@implementation FEProductDetailVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.productImageView sd_setImageWithURL:[NSURL URLWithString:kImageURL(self.product.picLabel)]];
    self.productDescription.text = self.product.desp;
    self.title = @"产品详情";
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end

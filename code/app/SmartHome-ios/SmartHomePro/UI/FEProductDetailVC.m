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
#import "NSString+Size.h"

@interface FEProductDetailVC ()
@property (strong, nonatomic) IBOutlet UIImageView *productImageView;
@property (strong, nonatomic) IBOutlet UILabel *productDescription;
@property (strong, nonatomic) IBOutlet UILabel *productTitleLabel;

@end

@implementation FEProductDetailVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.productImageView sd_setImageWithURL:[NSURL URLWithString:kImageURL(self.product.picLabel)]];
    self.productDescription.text = self.product.desp;
    self.productTitleLabel.text = self.product.name;
    self.title = @"产品详情";
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.row == 0) {
        CGSize size = [self.product.name boundingRectWithSize:CGSizeMake(self.view.bounds.size.width - 20, 9999) withTextFont:[UIFont systemFontOfSize:16]];
        return size.height + 20;
    }else if(indexPath.row == 1){
         CGSize size = [self.product.desp boundingRectWithSize:CGSizeMake(self.view.bounds.size.width - 20, 9999) withTextFont:[UIFont systemFontOfSize:16]];
        return size.height + 20;
    }
    return 44;
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

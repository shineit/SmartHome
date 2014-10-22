//
//  FECoreDataHandler.m
//  SmartHome
//
//  Created by Seven on 14-10-22.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECoreDataHandler.h"
#import "AppDelegate.h"
#import "FEUser.h"

@implementation FESortDescriptor
@synthesize key;
@synthesize ascending;

+ (FESortDescriptor *)sortDescriptorWithKey:(NSString *)key ascending:(BOOL)ascending {
    FESortDescriptor *descriptor = [[FESortDescriptor alloc] init];
    descriptor.key = key;
    descriptor.ascending = ascending;
    return descriptor;
}

@end


@interface FECoreDataHandler (){
}

- (NSArray *)fetchEntityByName:(NSString *)entityName
                     predicate:(NSPredicate *)predicate
                      sortKeys:(NSArray *)sortKeys
                    fetchLimit:(NSUInteger)fetchLimit
                affectedStores:(NSArray *)affectedStores
                    resultType:(NSFetchRequestResultType)resultType
           includesSubentities:(BOOL)includesSubentities
        includesPropertyValues:(BOOL)includesPropertyValues
        returnsObjectsAsFaults:(BOOL)returnsObjectsAsFaults
relationshipKeyPathsForPrefetching:(NSArray *)relationshipKeyPathsForPrefetching
        includesPendingChanges:(BOOL)includesPendingChanges
        returnsDistinctResults:(BOOL)returnsDistinctResults
             propertiesToFetch:(NSArray *)propertiesToFetch
                   fetchOffset:(NSUInteger)fetchOffset
                fetchBatchSize:(NSUInteger)fetchBatchSize
 shouldRefreshRefetchedObjects:(BOOL)shouldRefreshRefetchedObjects
           propertiesToGroupBy:(NSArray *)propertiesToGroupBy
               havingPredicate:(NSPredicate *)havingPredicate;

- (NSArray *)fetchEntityByName:(NSString *)entityName
                     predicate:(NSPredicate *)predicate
                      sortKeys:(NSArray *)sortKeys
                    fetchLimit:(NSUInteger)fetchLimit
             propertiesToFetch:(NSArray *)propertiesToFetch;

@end

@implementation FECoreDataHandler

@synthesize managedObjectContext = _managedObjectContext;

/*
 *  Get ManagedObjectContext
 */
- (instancetype)initWithAppDelegateManagedObjectContext {
    if (self = [super init]) {
        self.managedObjectContext = [AppDelegate sharedDelegate].managedObjectContext;
    }
    return self;
}

#pragma mark - private methods
/*
 *  Fetch Entities
 */
- (NSArray *)fetchEntityByName:(NSString *)entityName
                     predicate:(NSPredicate *)predicate
                      sortKeys:(NSArray *)sortKeys
                    fetchLimit:(NSUInteger)fetchLimit
                affectedStores:(NSArray *)affectedStores
                    resultType:(NSFetchRequestResultType)resultType
           includesSubentities:(BOOL)includesSubentities
        includesPropertyValues:(BOOL)includesPropertyValues
        returnsObjectsAsFaults:(BOOL)returnsObjectsAsFaults
relationshipKeyPathsForPrefetching:(NSArray *)relationshipKeyPathsForPrefetching
        includesPendingChanges:(BOOL)includesPendingChanges
        returnsDistinctResults:(BOOL)returnsDistinctResults
             propertiesToFetch:(NSArray *)propertiesToFetch
                   fetchOffset:(NSUInteger)fetchOffset
                fetchBatchSize:(NSUInteger)fetchBatchSize
 shouldRefreshRefetchedObjects:(BOOL)shouldRefreshRefetchedObjects
           propertiesToGroupBy:(NSArray *)propertiesToGroupBy
               havingPredicate:(NSPredicate *)havingPredicate
{
    //初始化FetchRequest
    NSFetchRequest *fetchRequest = [NSFetchRequest fetchRequestWithEntityName:entityName];
    
    //设置Predicate
    [fetchRequest setPredicate:predicate];
    
    //设置SortDescriptors数组
    NSMutableArray *sortDescriptors = [NSMutableArray array];
    for (FESortDescriptor *descriptor in sortKeys) {
        NSSortDescriptor *sortDescriptor = [NSSortDescriptor sortDescriptorWithKey:descriptor.key ascending:descriptor.ascending];
        [sortDescriptors addObject:sortDescriptor];
    }
    [fetchRequest setSortDescriptors:sortDescriptors];
    
    //设置数量限制，默认为0
    [fetchRequest setFetchLimit:fetchLimit];
    
    //设置数据源NSPersistentStoreCoordinator的数组，默认为AppDelegate的persistentStoreCoordinator
    [fetchRequest setAffectedStores:affectedStores];
    
    //设置返回类型NSManagedObjectResultType/NSManagedObjectIDResultType/NSDictionaryResultType/NSCountResultType，默认为NSManagedObjectResultType
    [fetchRequest setResultType:resultType];
    
    //设置是否查询子类，默认为YES
    [fetchRequest setIncludesSubentities:includesSubentities];
    
    //设置是否获取结果的具体Value，默认为YES，如果不获取将在获取对象属性时调用数据源获取具体Value
    [fetchRequest setIncludesPropertyValues:includesPropertyValues];
    
    //设置是否在出错时依然返回对象，默认为YES
    [fetchRequest setReturnsObjectsAsFaults:returnsObjectsAsFaults];
    
    //设置预处理的关系数组，如果设置了预处理的关系，则在获取该关系时是不用重新调用数据源获取关系的具体Value的
    [fetchRequest setRelationshipKeyPathsForPrefetching:relationshipKeyPathsForPrefetching];
    
    //设置是否包括已改变但未保存的数据对象，默认为YES
    [fetchRequest setIncludesPendingChanges:includesPendingChanges];
    
    //设置是否只返回propertiesToFetch数组中的相应Value，只对返回类型为NSDictionaryResultType有效，默认为NO；如果设置为YES，则根据propertiesToFetch数组中属性对应的Value，取不重复的字典返回，如果设置为NO，则取所有返回数据中propertiesToFetch数组包含的属性的Value
    [fetchRequest setReturnsDistinctResults:returnsDistinctResults];
    
    //设置预处理的属性，如果设置了预处理的属性，则在获取该属性时是不用重新调用数据源获取属性的具体Value的
    [fetchRequest setPropertiesToFetch:propertiesToFetch];
    
    //设置跳过前fetchOffset个返回结果，默认为0
    [fetchRequest setFetchOffset:fetchOffset];
    
    //设置每一批次的搜索数量，当满足fetchBatchSize个时即返回结果然后继续搜索直至结束
    [fetchRequest setFetchBatchSize:fetchBatchSize];
    
    //设置返回结果是否更新为已修改的Value，默认为NO
    [fetchRequest setShouldRefreshRefetchedObjects:shouldRefreshRefetchedObjects];
    
    //设置返回结果分组，只对返回类型为NSDictionaryResultType有效
    [fetchRequest setPropertiesToGroupBy:propertiesToGroupBy];
    
    //设置返回结果中的Predicate
    [fetchRequest setHavingPredicate:havingPredicate];
    
    //    [self.managedObjectContext performSelector:@selector(executeFetchRequest:error:) onThread:[NSThread mainThread] withObject:fetchRequest waitUntilDone:YES];
    
    // Fetch the results.
    //    @try {
    NSError *error = nil;
    NSArray *results = [self.managedObjectContext executeFetchRequest:fetchRequest error:&error];
    if (results == nil) {
        NSLog(@"Unresolved Core Data Fetch error %@, %@", error, [error userInfo]);
        //            abort();
    }
    return results;
    //    }
    //    @catch (NSException *exception) {
    //        NSLog(@"********Coredata fetch exception : %@",exception);
    //        NSAssert(NO, @"fetch error,for debug to solve");
    //    }
    //    @finally {
    //        return @[];
    //    }
    //    return results;
}



- (NSArray *)fetchEntityByName:(NSString *)entityName
                     predicate:(NSPredicate *)predicate
                      sortKeys:(NSArray *)sortKeys
                    fetchLimit:(NSUInteger)fetchLimit
             propertiesToFetch:(NSArray *)propertiesToFetch {
    return [self fetchEntityByName:entityName predicate:predicate
                          sortKeys:sortKeys
                        fetchLimit:fetchLimit
                    affectedStores:nil
                        resultType:NSManagedObjectResultType
               includesSubentities:YES
            includesPropertyValues:YES
            returnsObjectsAsFaults:YES
relationshipKeyPathsForPrefetching:nil
            includesPendingChanges:YES
            returnsDistinctResults:NO
                 propertiesToFetch:propertiesToFetch
                       fetchOffset:0
                    fetchBatchSize:0
     shouldRefreshRefetchedObjects:NO
               propertiesToGroupBy:nil
                   havingPredicate:nil];
}

- (NSArray *)fetchEntityByName:(NSString *)entityName
                     predicate:(NSPredicate *)predicate
                      sortKeys:(NSArray *)sortKeys
                    fetchLimit:(NSUInteger)fetchLimit
                   fetchOffset:(NSInteger)fetchOffset
             propertiesToFetch:(NSArray *)propertiesToFetch {
    return [self fetchEntityByName:entityName predicate:predicate
                          sortKeys:sortKeys
                        fetchLimit:fetchLimit
                    affectedStores:nil
                        resultType:NSManagedObjectResultType
               includesSubentities:YES
            includesPropertyValues:YES
            returnsObjectsAsFaults:YES
relationshipKeyPathsForPrefetching:nil
            includesPendingChanges:YES
            returnsDistinctResults:NO
                 propertiesToFetch:propertiesToFetch
                       fetchOffset:fetchOffset
                    fetchBatchSize:0
     shouldRefreshRefetchedObjects:NO
               propertiesToGroupBy:nil
                   havingPredicate:nil];
}

- (NSArray *)fetchEntityByName:(NSString *)entityName predicate:(NSPredicate *)predicate sortKeys:(NSArray *)sortKeys{
    return [self fetchEntityByName:entityName predicate:predicate
                          sortKeys:sortKeys
                        fetchLimit:0
                    affectedStores:nil
                        resultType:NSManagedObjectResultType
               includesSubentities:YES
            includesPropertyValues:YES
            returnsObjectsAsFaults:YES
relationshipKeyPathsForPrefetching:nil
            includesPendingChanges:YES
            returnsDistinctResults:NO
                 propertiesToFetch:nil
                       fetchOffset:0
                    fetchBatchSize:0
     shouldRefreshRefetchedObjects:NO
               propertiesToGroupBy:nil
                   havingPredicate:nil];
}

#pragma mark - Save methods
- (void)saveCoreData {
    [[AppDelegate sharedDelegate] saveContext];
}

#pragma mark - Delete methods
- (void)deleteCoreData:(NSArray *)array {
    for (NSManagedObject *object in array) {
        [self.managedObjectContext deleteObject:object];
    }
}

#pragma mark - FEUser
-(FEUser *)fetchUser{
    NSArray *array = [self fetchEntityByName:@"FEUser"
                                   predicate:nil
                                    sortKeys:nil];
    return array.lastObject;
}

-(FEUser *)touchUserByIdentifier:(NSString *)identifier{
    FEUser *user = [self fetchUser];
    if (!user) {
        user = (FEUser *)[NSEntityDescription insertNewObjectForEntityForName:@"FEUser" inManagedObjectContext:self.managedObjectContext];
        user.userid = identifier;
    }
    return user;
}


@end

import numpy as np
import pandas as pd


pd.set_option('display.max_rows', 1080, 'display.max_columns', 100)
alldata = pd.read_csv(
    'titanic.tsv',
    header=0,
    sep='\t',
    usecols=[
        'Survived',  # ok
        'PassengerId',  # irrelevant; dropped ok
        'Pclass',  # ok
        'Name',  # extract titles ok
        'Sex',  # ok
        'Age',  # NaN; float; small numbers == children? (eg. 0.67) ok
        'SibSp',  # ok
        'Parch',  # ok
        'Ticket',  # str; LINE?; dropped ok
        'Fare',  # who has 0 fare?  ok
        'Cabin',  # str; 486 NaN; NaN means no data or no cabin?; maybe -> 0; 1 ok
        'Embarked',  # str; NaN ok
    ],
)


# PassengerId
alldata = alldata.drop(columns='PassengerId')  # delete irrelevant column

# Name
# extract formal titles
alldata['Name'] = alldata['Name'].apply(
    lambda x: x.split('\t')[1].split()[0]
)
# uncommon formal titles to NaN values
alldata['Name'] = alldata['Name'].apply(
    lambda x: x if x in ['Mr.', 'Miss.', 'Mrs.', 'Master.', 'Dr.', 'Rev.'] else np.nan
)
alldata = alldata.dropna(subset='Name')  # delete NaN rows in Name column
alldata = pd.get_dummies(alldata, columns=['Name'], dtype=int)

# Sex
alldata = pd.get_dummies(alldata, columns=['Sex'], dtype=int)

# Age
ageAverage = alldata['Age'].mean(skipna=True)  # average age to fill in NaN's
ageAverage = round(ageAverage, 2)  # round age to two decimal places
alldata['Age'] = alldata['Age'].fillna(ageAverage)  # replace NaN's values with avg age

# Ticket
alldata = alldata.drop(columns='Ticket')  # delete Ticket column (irrelevant)

# Cabin
alldata['Cabin'] = alldata['Cabin'].fillna(0)  # replace NaN's values with 0

# count number of cabins
alldata['Cabin'] = alldata['Cabin'].apply(
    lambda x: 0 if isinstance(x, int) else len(x.split())
)

# Embarked
alldata = alldata.dropna(subset='Embarked')  # delete NaN rows in Embarked column
alldata = pd.get_dummies(alldata, columns=['Embarked'], dtype=int)


# print(alldata)
# print(alldata.dtypes)
# print(alldata['Cabin'].value_counts(dropna=False))
# print(pd.unique(alldata['Cabin']))
# print(alldata.isna().sum())

alldata.to_csv('titanic_numeric.tsv', sep='\t', index=False)
